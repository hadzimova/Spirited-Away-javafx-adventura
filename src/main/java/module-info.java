module cz.vse.adventurahadz01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.adventurahadz01 to javafx.fxml;
    exports cz.vse.adventurahadz01;
}