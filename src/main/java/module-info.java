module cz.vse.adventurahadz01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens cz.vse.adventurahadz01.main to javafx.fxml;
    exports cz.vse.adventurahadz01.main;
}