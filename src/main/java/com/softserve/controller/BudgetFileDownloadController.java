package com.softserve.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.entity.BudgetMetodURL;
import com.softserve.entity.DownloadDetail;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

@SuppressWarnings("serial")
@WebServlet("/budget-download")
public class BudgetFileDownloadController extends HttpServlet {

    protected static final String FILE_SAVE = "src\\main\\resources\\DatabaseBudget.xls";
    protected static final Logger logger = LoggerFactory.getLogger(BudgetFileDownloadController.class);
    protected static String download_URL = null;
    protected static String nameFile = null;
    protected static int yearDown = 0;
    protected static int downloadedSize = 0;
    protected static double filesize = 0.0;
    protected static int countURL = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/budget-download.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws NumberFormatException, IOException, ServletException {

        String selectYear = request.getParameter("selectYear");

        if (request.getParameter("selectYear") != null) {
            if ((getMetodURL(selectYear).equals("Nothing"))) {
                request.setAttribute("message", "Будь-ласка, оберіть рік! (Malformed_URL_Exception).");
                request.setAttribute("FILE_SAVE", downloadDetail());
                request.getRequestDispatcher("/WEB-INF/pages/budget-message.jsp").forward(request, response);

            } else {
                System.out.println("The data file will be downloaded at the link: " + getMetodURL(selectYear));
                downloadFile(openConnection(getMetodURL(selectYear)));

                if (getStatus().equals("Error!")) {
                    request.setAttribute("message", "Проблема із завантаженням файлу (ERR_INTERNET_DISCONNECTED).");
                } else {
                    request.setAttribute("message", "Файл успішно завантажений.");
                }

                request.setAttribute("FILE_SAVE", downloadDetail());
                request.getRequestDispatcher("/WEB-INF/pages/budget-message.jsp").forward(request, response);
            }

        } else {
            System.out.println("The selected year or file download link is invalid!");
            request.setAttribute("message", "Обраний рік або посилання на завантаження файлу є недійсними");
            request.getRequestDispatcher("/WEB-INF/pages/budget-message.jsp").forward(request, response);
        }
        loadFactor();
    }

    public static String getMetodURL(String selectYear) {
        System.out.print("Selected year: ");
        System.out.println(selectYear);
        yearDown = Integer.parseInt(selectYear);
        download_URL = BudgetMetodURL.switchYear(yearDown);
        return download_URL;
    }

    public static HttpURLConnection openConnection(String download_URL) {
        URL url = null;
        HttpURLConnection http = null;
        if (download_URL != null) {
            try {
                url = new URL(download_URL);
                nameFile = FilenameUtils.getName(url.getPath());
                http = (HttpURLConnection) url.openConnection();
                filesize = http.getContentLengthLong();
            } catch (MalformedURLException mue) {
                System.err.format("MalformedURLException: %s%n", mue);
            } catch (IOException ioe) {
                System.err.format("IOException: %s%n", ioe);
            }
            countURL++;
        }
        return http;
    }

    public static void downloadFile(HttpURLConnection http) {
        byte[] buffer = new byte[2048];
        double totalDownload = 0.0;
        int readbyte = 0;
        double percentOfDownload = 0.0;

        if (http != null) {
            try (BufferedInputStream input = new BufferedInputStream(http.getInputStream());
                 FileOutputStream ouputfile = new FileOutputStream(FILE_SAVE);
                 BufferedOutputStream bufferOut = new BufferedOutputStream(ouputfile, 2048)) {

                while ((readbyte = input.read(buffer, 0, 2048)) >= 0) {
                    bufferOut.write(buffer, 0, readbyte);
                    totalDownload += readbyte;
                    downloadedSize += readbyte;
                    percentOfDownload = (totalDownload * 100) / filesize;
                    String percent = String.format("%.2f", percentOfDownload);
                    System.out.println("Downloaded : " + percent + "%");
                }
            } catch (UnknownHostException | NullPointerException npi) {
                System.err.format("NullPointerException: %s%n", npi);
            } catch (IOException ioe) {
                System.err.format("IOException: %s%n", ioe);
            }
        }
    }

    public static String getStatus() {
        if (filesize > 0) {
            return "Success!";
        } else {
            return "Error!";
        }
    }

    public static List<DownloadDetail> downloadDetail() {
        DownloadDetail details = null;
        List<DownloadDetail> listDetails = new ArrayList<>();
        details = new DownloadDetail();
        details.setYearDownload(yearDown);
        details.setFileName(nameFile);
        details.setFileSize((long) ((filesize) / 1024));
        details.setDownloadStatus(getStatus());
        listDetails.add(details);

        System.out.println("Year: " + details.getYearDownload());
        System.out.println("Name: " + details.getFileName());
        System.out.println("Size: " + details.getFileSize() + " Kb");
        System.out.println("Status: " + details.getDownloadStatus());

        return listDetails;
    }

    public static void loadFactor() {
        if (filesize > 0) {
            System.out.println("How many downloads: " + countURL);
            System.out.println("Downloaded size, TOTAL: " + (downloadedSize / 1024) + " Kb");
            long loadFactor = ((long) ((downloadedSize - filesize) / 1024));
            System.out.println("Load factor: " + loadFactor + " Kb");

        } else {
            System.out.println("FALSE");
        }
    }

}