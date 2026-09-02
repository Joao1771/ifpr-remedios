module br.edu.ifpr.OfflineApp.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires lombok;
    requires org.hibernate.orm.core;
	requires javafx.base;
	requires javafx.graphics;
	requires org.apache.pdfbox;
	requires javafx.swing;

    opens br.edu.ifpr.OfflineApp.main to javafx.fxml;
    opens br.edu.ifpr.OfflineApp.controllers to javafx.fxml;
    opens br.edu.ifpr.OfflineApp.model;
    exports br.edu.ifpr.OfflineApp.main;
}
