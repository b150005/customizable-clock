package com.b150005.customizableclock;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

public class SettingsController implements Initializable {
  @FXML private RadioButton digitalClockModeRadioButton;
  @FXML private RadioButton digitalTimerModeRadioButton;
  @FXML private RadioButton analogClockModeRadioButton;

  @FXML private CheckBox displayAnimationInFrontCheckBox;
  @FXML private CheckBox frontAnimationIsOnCheckBox;
  @FXML private CheckBox frontAnimationDisplaysOnSpecificTimeCheckBox;
  @FXML private CheckBox backAnimationIsOnCheckBox;
  @FXML private CheckBox backAnimationDisplaysOnSpecificTimeCheckBox;
  @FXML private CheckBox specificTimeIsOnCheckBox1;
  @FXML private CheckBox specificTimeIsOnCheckBox2;
  @FXML private CheckBox specificTimeIsOnCheckBox3;
  @FXML private CheckBox specificTimeIsOnCheckBox4;
  @FXML private CheckBox specificTimeIsOnCheckBox5;

  @FXML private Label frontAnimationFilePathLabel;
  @FXML private Label backAnimationFilePathLabel;
  @FXML private Label faceFilePathLabel;
  @FXML private Label hourFilePathLabel;
  @FXML private Label minuteFilePathLabel;
  @FXML private Label secondFilePathLabel;

  @FXML private Button chooseFrontAnimationFileButton;
  @FXML private Button chooseBackAnimationFileButton;
  @FXML private Button chooseFaceFileButton;
  @FXML private Button chooseHourFileButton;
  @FXML private Button chooseMinuteFileButton;
  @FXML private Button chooseSecondFileButton;
  @FXML private Button chooseFontFileButton;
  
  @FXML private DatePicker specificDatePicker1;
  @FXML private DatePicker specificDatePicker2;
  @FXML private DatePicker specificDatePicker3;
  @FXML private DatePicker specificDatePicker4;
  @FXML private DatePicker specificDatePicker5;
  @FXML private DatePicker timerDatePicker;

  @FXML private ChoiceBox<Integer> specificHourChoiceBox1;
  @FXML private ChoiceBox<Integer> specificHourChoiceBox2;
  @FXML private ChoiceBox<Integer> specificHourChoiceBox3;
  @FXML private ChoiceBox<Integer> specificHourChoiceBox4;
  @FXML private ChoiceBox<Integer> specificHourChoiceBox5;
  @FXML private ChoiceBox<Integer> specificMinuteChoiceBox1;
  @FXML private ChoiceBox<Integer> specificMinuteChoiceBox2;
  @FXML private ChoiceBox<Integer> specificMinuteChoiceBox3;
  @FXML private ChoiceBox<Integer> specificMinuteChoiceBox4;
  @FXML private ChoiceBox<Integer> specificMinuteChoiceBox5;
  @FXML private ChoiceBox<Integer> specificSecondChoiceBox1;
  @FXML private ChoiceBox<Integer> specificSecondChoiceBox2;
  @FXML private ChoiceBox<Integer> specificSecondChoiceBox3;
  @FXML private ChoiceBox<Integer> specificSecondChoiceBox4;
  @FXML private ChoiceBox<Integer> specificSecondChoiceBox5;
  @FXML private ChoiceBox<Integer> timerHourChoiceBox;
  @FXML private ChoiceBox<Integer> timerMinuteChoiceBox;
  @FXML private ChoiceBox<Integer> timerSecondChoiceBox;
  
  @FXML private ColorPicker baseFontColorPicker;
  @FXML private ColorPicker beforeFontColorPicker;
  @FXML private ColorPicker afterFontColorPicker;

  @FXML private Spinner<Integer> beforeMinuteSpinner;
  @FXML private Spinner<Integer> afterMinuteSpinner;
  @FXML private Spinner<Integer> opacitySpinner;

  @FXML private Slider opacitySlider;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // TODO Auto-generated method stub
    
  }

  /**
   * 選択ボタンが押下された場合に呼び出される処理
   * @param event コンポーネントによって発火したActionEvent
   */
  @FXML protected void onChooseButtonClicked(ActionEvent event) {
    String dataType = "";
    String initialDirectory = System.getProperty("user.home") + File.separator + "Desktop";
    String extension = "*.gif";
    Path fontFolderPathForWin = Paths.get("C:\\Windows\\Fonts");

    switch (((Button)event.getSource()).getId()) {
    case "chooseFrontAnimationFileButton":
      dataType = "前面アニメーション";
      break;
    case "chooseBackAnimationFileButton":
      dataType = "アニメーション(背面)";
      break;
    case "chooseFaceFileButton":
      dataType = "時計盤";
      break;
    case "chooseHourFileButton":
      dataType = "短針";
      break;
    case "chooseMinuteFileButton":
      dataType = "長針";
      break;
    case "chooseSecondFileButton":
      dataType = "秒針";
      break;
    case "chooseFontFileButton":
      dataType = "フォント";
      extension = "*.ttf, *.ttc";

      // Windows かつ システム標準のフォントフォルダが存在する場合はFileChooserの初期ディレクトリに設定
      if ((System.getProperty("os.name").substring(0, 8) == "Windows") && (Files.exists(fontFolderPathForWin) == true)) {
        initialDirectory = fontFolderPathForWin.toString();
      }
      break;  
    }

    // FileChooserのオープン・ファイルの取得
    File file = getFilePathWithFileChooser(dataType + "の選択", initialDirectory, extension);

    // Fileが選択された場合は、ラベルを変更
    if (file != null) {
      String filePath = file.getAbsolutePath()
      switch (((Button)event.getSource()).getId()) {
      case "chooseFrontAnimationFileButton":
        frontAnimationFilePathLabel.setText(filePath);
        break;
      case "chooseBackAnimationFileButton":
        backAnimationFilePathLabel.setText(filePath);
        break;
      case "chooseFaceFileButton":
        faceFilePathLabel.setText(filePath);
        break;
      case "chooseHourFileButton":
        hourFilePathLabel.setText(filePath);
        break;
      case "chooseMinuteFileButton":
        minuteFilePathLabel.setText(filePath);
        break;
      case "chooseSecondFileButton":
        secondFilePathLabel.setText(filePath);
        break;
      case "chooseFontFileButton":
        // フォントの場合はChoiceBoxに追加・セット

        break;
      }
    }
  }

  /**
   * FileChooser(ファイル選択ダイアログ)を表示し、選択したFileオブジェクトを返却
   * @param title FileChooserのタイトルバーに表示する文字列
   * @param initialDirectory FileChooserダイアログが最初に表示するディレクトリ
   * @param extension 選択できる拡張子の種類
   * @return 選択したFileオブジェクト(Cancelを押した場合はnullが返却される)
   */
  private static File getFilePathWithFileChooser(String title, String initialDirectory, String extension) {
    FileChooser fileChooser = new FileChooser();
    // FileChooserのタイトル
    fileChooser.setTitle(title);
    // FileChooserの表示
    return fileChooser.showOpenDialog(null);
  }
}
