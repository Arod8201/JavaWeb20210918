package servlet.car;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/car_download")
public class CarDownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String car_photo = req.getParameter("car_photo");

		// 1.取得圖檔名稱
		if (car_photo == null) {
			resp.getWriter().print("Please input car_photo");
			return;
		}

		// 2.取得圖檔路徑
		File file = new File("c:/upload/" + car_photo);
		if (!file.exists()) {
			resp.getWriter().print("File not found : " + car_photo);
			return;
		}
		/**/
		// 3.讓瀏覽器直接下載檔案
		// 3.1. 設定網際網路媒體類型 MIME type
		String miniType = Files.probeContentType(file.toPath());
		System.out.println("miniType: " + miniType);
		if (miniType == null) {
			// 若無法判斷 mini type, 則可以任意設定一個二進制檔案 (強迫瀏覽器下載檔案)
			miniType = "application/oct-stream";
		}
		// 3.2. 設定下載檔案標頭資料(設定給瀏覽器看)
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
		resp.setHeader(headerKey, headerValue);

		// 4.將圖檔傳給 client 端
		Files.copy(file.toPath(), resp.getOutputStream());

	}
}
