package com.b150005.customizableclock;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
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
  @FXML private Button displayCustomizedClockButton;
  @FXML private Button reloadDigitalPreviewButton;
  @FXML private Button reloadAnalogPreviewButton;
  
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
  @FXML private ChoiceBox<String> fontChoiceBox;
  @FXML private ChoiceBox<Integer> timerHourChoiceBox;
  @FXML private ChoiceBox<Integer> timerMinuteChoiceBox;
  @FXML private ChoiceBox<Integer> timerSecondChoiceBox;
  
  @FXML private ColorPicker baseFontColorPicker;
  @FXML private ColorPicker beforeFontColorPicker;
  @FXML private ColorPicker afterFontColorPicker;

  @FXML private Spinner<Integer> beforeMinuteSpinner;
  @FXML private Spinner<Integer> afterMinuteSpinner;
  @FXML private Spinner<Double> opacitySpinner;

  @FXML private Slider opacitySlider;

  @FXML private StackPane analogPreviewStackPane;

  /**
   * FileChooserで選択したフォントファイル
   */
  private static File userFontFile;

  /**
   * JavaFXコンポーネントの初期化
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // DatePickerに現在日付をセット
    List<DatePicker> datePickerList = new ArrayList<DatePicker>(Arrays.asList(
      this.specificDatePicker1, 
      this.specificDatePicker2, 
      this.specificDatePicker3, 
      this.specificDatePicker4, 
      this.specificDatePicker5,
      this.timerDatePicker
    ));
    for(DatePicker dp: datePickerList) {
      dp.setValue(LocalDate.now());
    }

    // ChoiceBox(Hour)に0-23の値をセット
    ObservableList<Integer> hourValueList = FXCollections.observableArrayList();
    for (int i = 0; i < 24; i++) {
      hourValueList.add(i);
    }
    List<ChoiceBox<Integer>> hourChoiceBoxList = new ArrayList<ChoiceBox<Integer>>(Arrays.asList(
      this.specificHourChoiceBox1,
      this.specificHourChoiceBox2,
      this.specificHourChoiceBox3,
      this.specificHourChoiceBox4,
      this.specificHourChoiceBox5,
      this.timerHourChoiceBox
    ));
    for (ChoiceBox<Integer> cb: hourChoiceBoxList) {
      cb.setItems(hourValueList);
    }

    // ChoiceBox(Minute, Second)に0-59の値をセット
    ObservableList<Integer> minSecValueList = FXCollections.observableArrayList();
    for (int i = 0; i < 60; i++) {
      minSecValueList.add(i);
    }
    List<ChoiceBox<Integer>> minSecChoiceBoxList = new ArrayList<ChoiceBox<Integer>>(Arrays.asList(
      this.specificMinuteChoiceBox1,
      this.specificMinuteChoiceBox2,
      this.specificMinuteChoiceBox3,
      this.specificMinuteChoiceBox4,
      this.specificMinuteChoiceBox5,
      this.timerMinuteChoiceBox,
      this.specificSecondChoiceBox1,
      this.specificSecondChoiceBox2,
      this.specificSecondChoiceBox3,
      this.specificSecondChoiceBox4,
      this.specificSecondChoiceBox5,
      this.timerSecondChoiceBox
    ));
    for (ChoiceBox<Integer> cb: minSecChoiceBoxList) {
      cb.setItems(minSecValueList);
    }
    
    // フォントの一覧をChoiceBoxにセット
    ObservableList<String> fontNameList = getFontList();
    this.fontChoiceBox.setItems(fontNameList);
    this.fontChoiceBox.setValue(fontNameList.get(0));

    // Spinner(Opacity) ↔ Slider(Opacity) の値の連動
    this.opacitySpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
      this.opacitySlider.setValue(newValue);
    });
    this.opacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      this.opacitySpinner.getValueFactory().setValue((Double) newValue);
    });
  }

  public enum Buttons {
    FrontAnimation("chooseFrontAnimationFileButton", "アニメーション(前面)"),
    BackAnimation("chooseBackAnimationFileButton", "アニメーション(背面)"),
    Face("chooseFaceFileButton", "時計盤"),
    Hour("chooseHourFileButton", "短針"),
    Minute("chooseMinuteFileButton", "長針"),
    Second("chooseSecondFileButton", "秒針"),
    Font("chooseFontFileButton", "フォント");

    private final String id;
    private final String description;

    private Buttons(final String id, final String description) {
      this.id = id;
      this.description = description;
    }

    public String getId() {
      return this.id;
    }

    public String getDescription() {
      return this.description;
    }

    public static Buttons fromValue(final String value) {
      for (Buttons btn: values()) {
        if (btn.getId().equals(value) == true) {
          return btn;
        }
      }
      throw new IllegalArgumentException("undefined: " + value);
    }

    @Override
    public String toString() {
      return this.id;
    }
  }

  /**
   * 選択ボタンが押下された場合に呼び出される処理
   * @param event コンポーネントによって発火したActionEvent
   */
  @FXML protected void onChooseButtonClicked(ActionEvent event) {
    String dataType = "";
    String initialDirectory = System.getProperty("user.home") + File.separator + "Desktop";
    String description = "Gif Files";
    List<String> extensions = Arrays.asList("*.gif");
    Path fontFolderPathForWin = Paths.get("C:\\Windows\\Fonts");

    Buttons buttons = Buttons.fromValue(((Button)event.getSource()).getId());
    switch (buttons) {
    case Font:
      dataType = "フォント";
      description = "Font Files";
      extensions = Arrays.asList("*.ttf", "*.otf","*.ttc", "*.otc", "*.dfont", "*.eot", "*.woff", "*.woff2");

      // Windows かつ システム標準のフォントフォルダが存在する場合はFileChooserの初期ディレクトリに設定
      if ((System.getProperty("os.name").substring(0, 7).equals("Windows")) && (Files.exists(fontFolderPathForWin) == true)) {
        initialDirectory = fontFolderPathForWin.toString();
      }
      break;
    default:
      dataType = buttons.getDescription();
      break;
    }

    // FileChooserのオープン・ファイルの取得
    File file = getFilePathWithFileChooser(dataType + "の選択", initialDirectory, description, extensions);

    // Fileが選択された場合は、ラベルを変更
    if (file != null) {
      String filePath = file.getAbsolutePath();
      switch (buttons) {
      case FrontAnimation:
        frontAnimationFilePathLabel.setText(filePath);
        break;
      case BackAnimation:
        backAnimationFilePathLabel.setText(filePath);
        break;
      case Face:
        faceFilePathLabel.setText(filePath);
        break;
      case Hour:
        hourFilePathLabel.setText(filePath);
        break;
      case Minute:
        minuteFilePathLabel.setText(filePath);
        break;
      case Second:
        secondFilePathLabel.setText(filePath);
        break;
      case Font:
        // フォントの場合はChoiceBoxに追加・セット
        String fontNameWithExtension = file.getName();
        String fontName = fontNameWithExtension.substring(0, fontNameWithExtension.lastIndexOf('.'));
        String fontExtension = fontNameWithExtension.substring(fontNameWithExtension.lastIndexOf('.'), fontNameWithExtension.length());

        List<String> fontExtensionList = Arrays.asList(".ttf", ".otf",".ttc", ".otc", ".dfont", ".eot", ".woff", ".woff2");
        // 選択されたファイル拡張子がフォントファイルのものである場合はChoiceBoxに追加・セットし、フォントファイルのパスを保存
        if (fontExtensionList.contains(fontExtension) == true) {
          fontChoiceBox.getItems().add(fontName);
          fontChoiceBox.setValue(fontName);

          userFontFile = file;
        }
        break;
      }
    }
  }

  /**
   * FileChooser(ファイル選択ダイアログ)を表示し、選択したFileオブジェクトを返却
   * @param title FileChooserのタイトルバーに表示する文字列
   * @param initialDirectory FileChooserダイアログが最初に表示するディレクトリ
   * @param extensions 選択できるList<String>型の拡張子の種類
   * @return 選択したFileオブジェクト(Cancelを押した場合はnullが返却される)
   */
  private static File getFilePathWithFileChooser(String title, String initialDirectory, String description, List<String> extensions) {
    FileChooser fileChooser = new FileChooser();

    // FileChooserのタイトル
    fileChooser.setTitle(title);
    // 初期ディレクトリ
    fileChooser.setInitialDirectory(new File(initialDirectory));
    // FileChooserのExtensionFilter
    fileChooser.getExtensionFilters().addAll(
      new FileChooser.ExtensionFilter(description, extensions),
      new FileChooser.ExtensionFilter("All Files", "*.*")
    );
    // FileChooserの表示
    return fileChooser.showOpenDialog(null);
  }

  /**
   * ObservableList<String>型のフォント一覧を取得
   * @return ObservableList<String>型のフォント一覧
   */
  private static ObservableList<String> getFontList() {
    // フォントの一覧を取得
    List<String> fontNameList = Font.getFontNames();
    
    return FXCollections.observableArrayList(fontNameList);
  }

  /**
   * アナログ時計のプレビューを更新
   */
  @FXML protected void reloadAnalogPreview() {
    ImageView faceImageView = new ImageView("file:" + faceFilePathLabel.getText());
    ImageView hourImageView = new ImageView("file:" + hourFilePathLabel.getText());
    ImageView minuteImageView = new ImageView("file:" + minuteFilePathLabel.getText());
    ImageView secondImageView = new ImageView("file:" + secondFilePathLabel.getText());
    ImageView frontAnimationImageView = new ImageView("file:" + frontAnimationFilePathLabel.getText());
    ImageView backAnimationImageView = new ImageView("file:" + backAnimationFilePathLabel.getText());

    List<ImageView> clockImageViewList = new ArrayList<ImageView>(Arrays.asList(
      faceImageView,
      hourImageView,
      minuteImageView,
      secondImageView
    ));

    List<ImageView> animationImageViewList = new ArrayList<ImageView>();
    if (frontAnimationIsOnCheckBox.isSelected() == true) {
      animationImageViewList.add(frontAnimationImageView);
    }
    if (backAnimationIsOnCheckBox.isSelected() == true) {
      animationImageViewList.add(backAnimationImageView);
    }

    // Imageファイルが存在する場合はStackPaneに表示
    ObservableList<ImageView> imageViewObservableList= FXCollections.observableArrayList();
    if (displayAnimationInFrontCheckBox.isSelected() == true) {
      addFoundImageViews(animationImageViewList, imageViewObservableList);
      addFoundImageViews(clockImageViewList, imageViewObservableList);
    }
    else {
      addFoundImageViews(clockImageViewList, imageViewObservableList);
      addFoundImageViews(animationImageViewList, imageViewObservableList);
    }

    analogPreviewStackPane.getChildren().addAll(imageViewObservableList);
  }

  /**
   * Imageファイルが存在するImageViewをObservableList<ImageView>に追加する
   * @param fromList Imageファイルが存在するかどうかを調べるList<ImageView>型のリスト
   * @param toList Imageファイルが存在するImageViewを格納するObservableList<ImageView>型のリスト
   */
  private static void addFoundImageViews(List<ImageView> fromList, ObservableList<ImageView> toList) {
    for (ImageView imgView: fromList) {
      Image img = imgView.imageProperty().get();
      if (img.errorProperty().get() == true) {
        toList.add(imgView);
      }
    }
  }
}
