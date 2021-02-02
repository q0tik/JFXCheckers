module pro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires javafx.graphics;
    opens pro.q0tik to javafx.fxml;
    exports pro.q0tik;
}