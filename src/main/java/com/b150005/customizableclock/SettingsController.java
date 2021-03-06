package com.b150005.customizableclock;

import java.io.File;
import java.net.URL;
import java.nio.file.*;
import java.time.*;
import java.util.*;

import javafx.animation.*;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

public class SettingsController implements Initializable {
  @FXML private RadioButton digitalClockModeRadioButton;
  @FXML private RadioButton digitalTimerModeRadioButton;
  @FXML private RadioButton analogClockModeRadioButton;

  @FXML private CheckBox displayAnimationInFrontCheckBox;
  @FXML private CheckBox frontAnimationIsOnCheckBox;
  @FXML private CheckBox frontAnimationDisplaysOnSpecificTimeCheckBox;
  @FXML private CheckBox backAnimationIsOnCheckBox;
  @FXML private CheckBox backAnimationDisplaysOnSpecificTimeCheckBox;
  @FXML private CheckBox specificFrontAnimationIsOnCheckBox1;
  @FXML private CheckBox specificFrontAnimationIsOnCheckBox2;
  @FXML private CheckBox specificFrontAnimationIsOnCheckBox3;
  @FXML private CheckBox specificFrontAnimationIsOnCheckBox4;
  @FXML private CheckBox specificBackAnimationIsOnCheckBox1;
  @FXML private CheckBox specificBackAnimationIsOnCheckBox2;
  @FXML private CheckBox specificBackAnimationIsOnCheckBox3;
  @FXML private CheckBox specificBackAnimationIsOnCheckBox4;
  @FXML private CheckBox specificTimeIsOnCheckBox5;
  @FXML private CheckBox displayFrontAnimationCheckBox5;

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
  
  @FXML private DatePicker specificFrontAnimationDatePicker1;
  @FXML private DatePicker specificFrontAnimationDatePicker2;
  @FXML private DatePicker specificFrontAnimationDatePicker3;
  @FXML private DatePicker specificFrontAnimationDatePicker4;
  @FXML private DatePicker specificBackAnimationDatePicker1;
  @FXML private DatePicker specificBackAnimationDatePicker2;
  @FXML private DatePicker specificBackAnimationDatePicker3;
  @FXML private DatePicker specificBackAnimationDatePicker4;
  @FXML private DatePicker timerDatePicker;

  @FXML private ChoiceBox<Integer> specificFrontAnimationHourChoiceBox1;
  @FXML private ChoiceBox<Integer> specificFrontAnimationHourChoiceBox2;
  @FXML private ChoiceBox<Integer> specificFrontAnimationHourChoiceBox3;
  @FXML private ChoiceBox<Integer> specificFrontAnimationHourChoiceBox4;
  @FXML private ChoiceBox<Integer> specificBackAnimationHourChoiceBox1;
  @FXML private ChoiceBox<Integer> specificBackAnimationHourChoiceBox2;
  @FXML private ChoiceBox<Integer> specificBackAnimationHourChoiceBox3;
  @FXML private ChoiceBox<Integer> specificBackAnimationHourChoiceBox4;
  @FXML private ChoiceBox<Integer> specificFrontAnimationMinuteChoiceBox1;
  @FXML private ChoiceBox<Integer> specificFrontAnimationMinuteChoiceBox2;
  @FXML private ChoiceBox<Integer> specificFrontAnimationMinuteChoiceBox3;
  @FXML private ChoiceBox<Integer> specificFrontAnimationMinuteChoiceBox4;
  @FXML private ChoiceBox<Integer> specificBackAnimationMinuteChoiceBox1;
  @FXML private ChoiceBox<Integer> specificBackAnimationMinuteChoiceBox2;
  @FXML private ChoiceBox<Integer> specificBackAnimationMinuteChoiceBox3;
  @FXML private ChoiceBox<Integer> specificBackAnimationMinuteChoiceBox4;
  @FXML private ChoiceBox<Integer> specificFrontAnimationSecondChoiceBox1;
  @FXML private ChoiceBox<Integer> specificFrontAnimationSecondChoiceBox2;
  @FXML private ChoiceBox<Integer> specificFrontAnimationSecondChoiceBox3;
  @FXML private ChoiceBox<Integer> specificFrontAnimationSecondChoiceBox4;
  @FXML private ChoiceBox<Integer> specificBackAnimationSecondChoiceBox1;
  @FXML private ChoiceBox<Integer> specificBackAnimationSecondChoiceBox2;
  @FXML private ChoiceBox<Integer> specificBackAnimationSecondChoiceBox3;
  @FXML private ChoiceBox<Integer> specificBackAnimationSecondChoiceBox4;
  @FXML private ChoiceBox<String> fontChoiceBox;
  @FXML private ChoiceBox<String> fontWeightChoiceBox;
  @FXML private ChoiceBox<String> fontPostureChoiceBox;
  @FXML private ChoiceBox<Integer> timerHourChoiceBox;
  @FXML private ChoiceBox<Integer> timerMinuteChoiceBox;
  @FXML private ChoiceBox<Integer> timerSecondChoiceBox;
  
  @FXML private ColorPicker baseFontColorPicker;
  @FXML private ColorPicker beforeFontColorPicker;
  @FXML private ColorPicker afterFontColorPicker;

  /**
   * メイン画面を表示するStage
   */
  private static Stage mainStage = null;

  /**
   * メイン画面に表示するStackPaneを貼り付けるScene
   */
  private static Scene mainScene = null;

  @FXML private Spinner<Integer> widthSpinner;
  @FXML private Spinner<Integer> heightSpinner;
  @FXML private Spinner<Integer> beforeMinuteSpinner;
  @FXML private Spinner<Integer> afterMinuteSpinner;
  @FXML private Spinner<Double> opacitySpinner;

  @FXML private Slider opacitySlider;

  @FXML private StackPane analogPreviewStackPane;
  @FXML private StackPane digitalPreviewStackPane;
  /**
   * メイン画面のStackPane
   */
  private static StackPane mainStackPane = null;

  /**
   * FileChooserで選択したフォント
   */
  private static String userFontName = null;
  private static File userFontFile = null;

  /**
   * メイン画面に表示するアナログ時計の回転アニメーション
   */
  private static RotateTransition mainHourRotateTransition = null;
  private static RotateTransition mainMinRotateTransition = null;
  private static RotateTransition mainSecRotateTransition = null;

  /**
   * プレビューに表示するアナログ時計の回転アニメーション
   */
  private static RotateTransition previewHourRotateTransition = null;
  private static RotateTransition previewMinRotateTransition = null;
  private static RotateTransition previewSecRotateTransition = null;

  /**
   * プレビューに表示するアニメーション
   */
  private static Timeline previewAnimationTimeline = null;
  /**
   * メイン画面に表示するアニメーション
   */
  private static Timeline mainAnimationTimeline = null;
  /**
   * プレビューに表示するデジタル時計のラベルを変更するアニメーション
   */
  private static Timeline previewDigitalLabelTimeline = null;
  /**
   * メイン画面に表示するデジタル時計のラベルを変更するアニメーション
   */
  private static Timeline mainDigitalLabelTimeline = null;

  /**
   * MouseEventが発生した2次元座標
   */
  private double xOffset = 0;
  private double yOffset = 0;

  /**
   * メイン画面に表示するデジタル時計(タイマー)の各種情報
   */
  private static LocalDateTime mainLocalDateTime;
  private static int mainBeforeMin;
  private static int mainAfterMin;
  private static Color mainBaseFontColor;
  private static Color mainBeforeFontColor;
  private static Color mainAfterFontColor;
  /**
   * プレビューに表示するデジタル時計(タイマー)の各種情報
   */
  private static LocalDateTime previewLocalDateTime;
  private static int previewBeforeMin;
  private static int previewAfterMin;
  private static Color previewBaseFontColor;
  private static Color previewBeforeFontColor;
  private static Color previewAfterFontColor;

  /**
   * JavaFXコンポーネントの初期化
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // DatePickerに現在日付をセット
    List<DatePicker> datePickerList = new ArrayList<DatePicker>(Arrays.asList(
      this.specificFrontAnimationDatePicker1, 
      this.specificFrontAnimationDatePicker2, 
      this.specificFrontAnimationDatePicker3, 
      this.specificFrontAnimationDatePicker4, 
      this.specificBackAnimationDatePicker1,
      this.specificBackAnimationDatePicker2,
      this.specificBackAnimationDatePicker3,
      this.specificBackAnimationDatePicker4,
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
      this.specificFrontAnimationHourChoiceBox1,
      this.specificFrontAnimationHourChoiceBox2,
      this.specificFrontAnimationHourChoiceBox3,
      this.specificFrontAnimationHourChoiceBox4,
      this.specificBackAnimationHourChoiceBox1,
      this.specificBackAnimationHourChoiceBox2,
      this.specificBackAnimationHourChoiceBox3,
      this.specificBackAnimationHourChoiceBox4,
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
      this.specificFrontAnimationMinuteChoiceBox1,
      this.specificFrontAnimationMinuteChoiceBox2,
      this.specificFrontAnimationMinuteChoiceBox3,
      this.specificFrontAnimationMinuteChoiceBox4,
      this.specificBackAnimationMinuteChoiceBox1,
      this.specificBackAnimationMinuteChoiceBox2,
      this.specificBackAnimationMinuteChoiceBox3,
      this.specificBackAnimationMinuteChoiceBox4,
      this.timerMinuteChoiceBox,
      this.specificFrontAnimationSecondChoiceBox1,
      this.specificFrontAnimationSecondChoiceBox2,
      this.specificFrontAnimationSecondChoiceBox3,
      this.specificFrontAnimationSecondChoiceBox4,
      this.specificBackAnimationSecondChoiceBox1,
      this.specificBackAnimationSecondChoiceBox2,
      this.specificBackAnimationSecondChoiceBox3,
      this.specificBackAnimationSecondChoiceBox4,
      this.timerSecondChoiceBox
    ));
    for (ChoiceBox<Integer> cb: minSecChoiceBoxList) {
      cb.setItems(minSecValueList);
    }
    
    // フォントファミリの一覧をChoiceBoxにセット
    ObservableList<String> fontFamilyNameList = getFontFamilyList();
    this.fontChoiceBox.setItems(fontFamilyNameList);
    this.fontChoiceBox.setValue(fontFamilyNameList.get(0));

    // FontWeightをChoiceBoxにセット
    ObservableList<String> fontWeightList = FXCollections.observableArrayList(
      "Thin",         // 100
      "ExtraLight",   // 200
      "Light",        // 300
      "Normal",       // 400
      "Medium",       // 500
      "SemiBold",     // 600
      "Bold",         // 700
      "ExtraBold",    // 800
      "Black"         // 900
    );
    this.fontWeightChoiceBox.setItems(fontWeightList);
    this.fontWeightChoiceBox.setValue(fontWeightList.get(3));

    // FontPostureをChoiceBoxにセット
    ObservableList<String> fontPostureList = FXCollections.observableArrayList("Regular", "Italic");
    this.fontPostureChoiceBox.setItems(fontPostureList);
    this.fontPostureChoiceBox.setValue(fontPostureList.get(0));

    // ChoiceBox(フォント) で既存フォントが選択された場合はChoiceBoxからユーザ指定の値を取り除き、
    // static変数の値をnullにする
    this.fontChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (userFontName != null && newValue.equals(userFontName) == false) {
        this.fontChoiceBox.getItems().remove(userFontName);
        userFontName = null;
        userFontFile = null;
      }
    });

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
        userFontName = fontNameWithExtension.substring(0, fontNameWithExtension.lastIndexOf('.'));
        String fontExtension = fontNameWithExtension.substring(fontNameWithExtension.lastIndexOf('.'), fontNameWithExtension.length());

        List<String> fontExtensionList = Arrays.asList(".ttf", ".otf",".ttc", ".otc", ".dfont", ".eot", ".woff", ".woff2");
        // 選択されたファイル拡張子がフォントファイルのものである場合はChoiceBoxに追加・セットし、フォントファイルのパスを保存
        if (fontExtensionList.contains(fontExtension) == true) {
          fontChoiceBox.getItems().add(userFontName);
          fontChoiceBox.setValue(userFontName);

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
   * ObservableList<String>型のフォントファミリの一覧を取得
   * @return ObservableList<String>型のフォントファミリの一覧
   */
  private static ObservableList<String> getFontFamilyList() {
    // フォントの一覧を取得
    List<String> fontFamilyNameList = Font.getFamilies();
    
    return FXCollections.observableArrayList(fontFamilyNameList);
  }

  /**
   * アナログ時計のプレビューを更新
   */
  @FXML protected void reloadAnalogPreview() {
    // StackPaneへのアナログ時計gifの追加
    this.attachImageViewToStackPane(analogPreviewStackPane, PreviewType.Preview, previewHourRotateTransition, previewMinRotateTransition, previewSecRotateTransition);

    // StackPaneへのアニメーションの追加
    this.addAnimationsOntoStackPane(analogPreviewStackPane, previewAnimationTimeline);
  }

  private void attachImageViewToStackPane(StackPane stackPane, PreviewType previewType, RotateTransition hourRotateTransition, RotateTransition minRotateTransition, RotateTransition secRotateTransition) {
    // StackPaneの初期化
    stackPane.getChildren().clear();

    // RotateTransitionの初期化
    if (hourRotateTransition != null) {
      hourRotateTransition.stop();
      hourRotateTransition = null;
    }
    if (minRotateTransition != null) {
      minRotateTransition.stop();
      minRotateTransition = null;
    }
    if (secRotateTransition != null) {
      secRotateTransition.stop();
      secRotateTransition = null;
    }

    ImageView faceImageView = new ImageView("file:" + faceFilePathLabel.getText());
    ImageView hourImageView = new ImageView("file:" + hourFilePathLabel.getText());
    ImageView minuteImageView = new ImageView("file:" + minuteFilePathLabel.getText());
    ImageView secondImageView = new ImageView("file:" + secondFilePathLabel.getText());

    // アナログ時計のList
    List<ImageView> clockImageViewList = new ArrayList<ImageView>(Arrays.asList(
      faceImageView,
      hourImageView,
      minuteImageView,
      secondImageView
    ));

    // Imageファイルが存在する場合はStackPaneへの表示対象として追加
    // アニメーションの前面表示フラグに応じてObservableListに追加する順番を変更
    ObservableList<ImageView> willAlwaysShowImageViewObservableList= FXCollections.observableArrayList();
    if (displayAnimationInFrontCheckBox.isSelected() == true) {
      addFoundImageViews(clockImageViewList, willAlwaysShowImageViewObservableList);
    }
    else {
      addFoundImageViews(clockImageViewList, willAlwaysShowImageViewObservableList);
    }

    // StackPaneに表示するImageViewのサイズをStackPaneと連動
    // 幅と高さで小さい方に合わせる
    for (ImageView imgView: willAlwaysShowImageViewObservableList) {
      ReadOnlyDoubleProperty paneWidth = stackPane.widthProperty();
      ReadOnlyDoubleProperty paneHeight = stackPane.heightProperty();
      if (stackPane.getWidth() > stackPane.getHeight()) {
        imgView.fitWidthProperty().bind(paneHeight);
        imgView.fitHeightProperty().bind(paneHeight);
      }
      else {
        imgView.fitWidthProperty().bind(paneWidth);
        imgView.fitHeightProperty().bind(paneWidth);
      }
    }

    // 常時表示するImageViewのセット
    stackPane.getChildren().addAll(willAlwaysShowImageViewObservableList);

    // 現在時刻に応じた回転アニメーションの再生
    // 第一引数がPreviewType.Previewの場合はCycleCountが有限のためstop()しない
    LocalTime time = LocalTime.now();
    hourRotateTransition = createRotateTransition(previewType, Duration.hours(12), hourImageView, getInitialAngle(time, TimeType.Hour));
    hourRotateTransition.play();
    minRotateTransition = createRotateTransition(previewType, Duration.minutes(60), minuteImageView, getInitialAngle(time, TimeType.Minute));
    minRotateTransition.play();
    secRotateTransition = createRotateTransition(previewType, Duration.seconds(60), secondImageView, getInitialAngle(time, TimeType.Second));
    secRotateTransition.play();
  }

  /**
   * Imageファイルが存在するImageViewをObservableList<ImageView>に追加する
   * @param fromList Imageファイルが存在するかどうかを調べるList<ImageView>型のリスト
   * @param toList Imageファイルが存在するImageViewを格納するObservableList<ImageView>型のリスト
   */
  private static void addFoundImageViews(List<ImageView> fromList, ObservableList<ImageView> toList) {
    for (ImageView imgView: fromList) {
      Image img = imgView.imageProperty().get();
      if (img.errorProperty().get() == false) {
        toList.add(imgView);
      }
    }
  }

  /**
   * アニメーションのCycleCount(繰り返し回数)を定義する列挙型
   * Main: メイン画面
   * Preview: プレビュー画面
   */
  public enum PreviewType {
    Main, Preview;
  };

  /**
   * 回転アニメーションを表すRotateTransitionインスタンスを生成
   * @param type アニメーションを表示する画面の種類, PreviewType.Mainの場合は繰り返し回数が無限となり、PreviewType.Previewの場合は1回のみ
   * @param duration アニメーション1回あたりの時間
   * @param imageView アニメーションを適用するImageView
   * @param initialAngle 初期回転角度
   * @return RotateTransition
   */
  private static RotateTransition createRotateTransition(PreviewType type, Duration duration, ImageView imageView, double initialAngle) {
    RotateTransition rotateTransition = new RotateTransition(duration, imageView);

    // 初期角度
    rotateTransition.setFromAngle(initialAngle);
    // Duration内に回転する角度
    rotateTransition.setByAngle(360);
    // アニメーションを繰り返す
    switch (type) {
      case Main:
        rotateTransition.setCycleCount(Animation.INDEFINITE);
      case Preview:
        rotateTransition.setCycleCount(1);
    }
    
    // 等速アニメーション
    rotateTransition.setInterpolator(Interpolator.LINEAR);

    return rotateTransition;
  }

  public enum TimeType {
    Hour, Minute, Second
  };

  /**
   * TimeTypeに応じたdouble型の初期回転角度を算出する
   * @param time 計算に必要なLocalTime(現在時刻)
   * @param timeType 時・分・秒を表すTimeType
   * @return double型の回転角度
   */
  private static double getInitialAngle(LocalTime time, TimeType timeType) {
    double result = 0;
    
    switch (timeType) {
      case Hour:
        result = (double) ((time.getHour() % 12 + time.getMinute() / 60d + time.getSecond() / 3600d) * 360 / 12);
        break;
      case Minute:
        result = (double) ((time.getMinute() + time.getSecond() / 60d) * 360 / 60);
        break;
      case Second:
        result = (double) time.getSecond() * 360 / 60;
        break;
    }

    return result;
  }

  /**
   * デジタル時計(タイマー)のプレビューを更新
   */
  @FXML protected void reloadDigitalPreview() {
    /**
     * 各種情報の取得
     */
    if (timerDatePicker.getValue() != null && timerHourChoiceBox.getValue() != null && timerMinuteChoiceBox.getValue() != null && timerSecondChoiceBox.getValue() != null) {
      previewLocalDateTime = LocalDateTime.of(
        timerDatePicker.getValue(), 
        LocalTime.of(
          timerHourChoiceBox.getValue(), 
          timerMinuteChoiceBox.getValue(), 
          timerSecondChoiceBox.getValue()
        )
      );
    }
    else {
      previewLocalDateTime = LocalDateTime.now().plusHours(1);
    }
    previewBeforeMin = beforeMinuteSpinner.getValue();
    previewAfterMin = afterMinuteSpinner.getValue();
    previewBaseFontColor = baseFontColorPicker.getValue();
    previewBeforeFontColor = beforeFontColorPicker.getValue();
    previewAfterFontColor = afterFontColorPicker.getValue();

    // StackPaneへのLabelの追加
    this.attachLabelToStackPane(digitalPreviewStackPane, previewDigitalLabelTimeline, previewLocalDateTime, previewBeforeMin, previewAfterMin, previewBaseFontColor, previewBeforeFontColor, previewAfterFontColor);

    // StackPaneへのアニメーションの追加
    this.addAnimationsOntoStackPane(digitalPreviewStackPane, previewAnimationTimeline);
  }

  /**
   * デジタル時計(タイマー)を表示するLabelを生成しStackPaneに貼り付ける
   * @param stackPane Labelを貼り付けるStackPane
   * @param labelTimeline デジタル時計(タイマー)を反映するTimeline
   */
  private void attachLabelToStackPane(StackPane stackPane, Timeline labelTimeline, LocalDateTime localDateTime, int beforeMin, int afterMin, Color baseFontColor, Color beforeFontColor, Color afterFontColor) {
    // StackPaneの初期化
    stackPane.getChildren().clear();

    // Timelineの初期化
    if (labelTimeline != null) {
      labelTimeline.stop();
      labelTimeline = null;
    }

    // デジタル時計/タイマーとして表示するLabel
    Label timerLabel = new Label();

    // フォントの設定
    final double defaultFontSize = 100;
    timerLabel.textProperty().addListener((observable, oldValue, newValue) -> {
      // Textを利用してmaxFontSize下での文字列の表示幅を取得
      Text text = new Text(newValue);

      // ChoiceBoxで選択された値
      String fontFamilyName = fontChoiceBox.getValue();

      FontWeight fontWeight = FontWeight.NORMAL;
      switch (fontWeightChoiceBox.getValue()) {
        case "Thin":
          fontWeight = FontWeight.THIN;
          break;
        case "ExtraLight":
          fontWeight =  FontWeight.EXTRA_LIGHT;
          break;
        case "Light":
          fontWeight = FontWeight.LIGHT;
          break;
        case "Normal":
          fontWeight = FontWeight.NORMAL;
          break;
        case "Medium":
          fontWeight = FontWeight.MEDIUM;
          break;
        case "SemiBold":
          fontWeight = FontWeight.SEMI_BOLD;
          break;
        case "Bold":
          fontWeight = FontWeight.BOLD;
          break;
        case "ExtraBold":
          fontWeight = FontWeight.EXTRA_BOLD;
          break;
        case "Black":
          fontWeight = FontWeight.BLACK;
          break;
      }

      FontPosture fontPosture = FontPosture.REGULAR;
      switch (fontPostureChoiceBox.getValue()) {
        case "Regular":
          fontPosture = FontPosture.REGULAR;
          break;
        case "Italic":
          fontPosture = FontPosture.ITALIC;
          break;
      }

      // フォントサイズの計算で用いるFont
      Font tempFont;
      // ユーザ独自フォントの場合
      if (userFontName != null && userFontFile != null) {
        tempFont = Font.loadFont("file:" + userFontFile.getAbsolutePath(), defaultFontSize);
      }
      // 標準フォントの場合
      else {
        tempFont = Font.font(fontFamilyName, fontWeight, fontPosture, defaultFontSize);
      }
      text.setFont(tempFont);
      double textWidth = text.getLayoutBounds().getWidth();

      double newFontSize = defaultFontSize * (stackPane.getWidth() / textWidth);
      Font displayedFont;
      // ユーザの独自フォントが選択されている場合は独自フォントファイルを読み込んで表示
      if (userFontName != null && userFontFile != null) {
        displayedFont = Font.loadFont("file:" + userFontFile.getAbsolutePath(), newFontSize);
      }
      else {
        displayedFont = Font.font(fontFamilyName, fontWeight, fontPosture, newFontSize);
      }

      // Labelに調整したフォントを反映
      timerLabel.setFont(displayedFont);
    });

    // Labelの透明度(opacity)
    timerLabel.setOpacity(opacitySpinner.getValue());

    // LabelをStackPaneに追加
    stackPane.getChildren().add(timerLabel);
    
    // Timelineを用いたデジタル時計の描画
    if (digitalClockModeRadioButton.isSelected() == true) {
      // フォント色・透明度の設定
      timerLabel.setTextFill(baseFontColorPicker.getValue());
      timerLabel.setOpacity(opacitySpinner.getValue());

      labelTimeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          LocalTime time = LocalTime.now();
          String text = String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());
          timerLabel.setText(text);
        }
      }));
    }
    // Timelineを用いたデジタルタイマーの描画
    else if (digitalTimerModeRadioButton.isSelected() == true &&
    timerDatePicker.getValue() != null &&
    timerHourChoiceBox.getValue() != null &&
    timerSecondChoiceBox.getValue() != null) {
      labelTimeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          // 現在時刻と指定日時を取得
          LocalDateTime now = LocalDateTime.now();

          // 2つのLocalDateTimeの差分(Duration)を取得
          java.time.Duration duration = java.time.Duration.between(now, localDateTime);
          int hour;
          if ((duration.toSeconds() >= 0) || (duration.toSeconds() < 0 && duration.toHours() < 0)) {
            hour = (int) duration.toHours();
          }
          else {
            hour = (int) -duration.toHours();
          }
          int min = duration.toMinutes() >= 0 ? (int) duration.toMinutes() % 60 : (int) -duration.toMinutes() % 60;
          int sec = duration.toSeconds() >= 0 ? (int) duration.toSeconds() % 60 : (int) -duration.toSeconds() % 60;

          // フォント色・透明度の設定
          if (duration.toSeconds() >= 0 && duration.toSeconds() <= beforeMin * 60) {
            timerLabel.setTextFill(beforeFontColor);
          }
          else if (duration.toSeconds() < 0 && -duration.toSeconds() <= afterMin * 60) {
            timerLabel.setTextFill(afterFontColor);
          }
          else {
            timerLabel.setTextFill(baseFontColor);
          }

          String text = String.format("%02d:%02d:%02d", hour, min, sec);
          timerLabel.setText(text);
        }
      }));
    }

    // Labelに反映するTimelineの設定
    labelTimeline.setCycleCount(Timeline.INDEFINITE);
    labelTimeline.play();
  }

  /**
   * アニメーション以外のNodeが追加された状態のStackPaneにアニメーションを追加する
   * @param stackPane アニメーション以外のNodeが追加された状態のStackPane
   * @param frontAnimationUrl アニメーション(前面)のURL
   * @param backAnimationUrl アニメーション(背面)のURL
   */
  private void addAnimationsOntoStackPane(StackPane stackPane, Timeline animationTimeline) {
    // Timelineの初期化
    if (animationTimeline != null) {
      animationTimeline.stop();
      animationTimeline = null;

      System.out.println("Timelineを停止");
    }

    // アニメーションを表示しない設定の場合は何もしない
    if (frontAnimationIsOnCheckBox.isSelected() == false && backAnimationIsOnCheckBox.isSelected() == false) {
      return;
    }

    // アニメーションを表示する場合は常時アニメーションを表示するためのImageViewを追加しておく
    ImageView frontAnimationImageView = new ImageView();
    ImageView backAnimationImageView = new ImageView();
    // アニメーションImageViewの幅・高さをStackPaneにバインド
    ReadOnlyDoubleProperty paneWidth = stackPane.widthProperty();
    ReadOnlyDoubleProperty paneHeight = stackPane.heightProperty();
    if (stackPane.getWidth() > stackPane.getHeight()) {
      frontAnimationImageView.fitWidthProperty().bind(paneHeight);
      frontAnimationImageView.fitHeightProperty().bind(paneHeight);
      backAnimationImageView.fitWidthProperty().bind(paneHeight);
      backAnimationImageView.fitHeightProperty().bind(paneHeight);
    }
    else {
      frontAnimationImageView.fitWidthProperty().bind(paneWidth);
      frontAnimationImageView.fitHeightProperty().bind(paneWidth);
      backAnimationImageView.fitWidthProperty().bind(paneWidth);
      backAnimationImageView.fitHeightProperty().bind(paneWidth);
    }
    // アニメーションを前面に表示する場合
    if (displayAnimationInFrontCheckBox.isSelected() == true) {
      stackPane.getChildren().add(backAnimationImageView);
      stackPane.getChildren().add(frontAnimationImageView);
    }
    // アニメーションを背面に表示する場合
    else {
      stackPane.getChildren().add(0, backAnimationImageView);
      stackPane.getChildren().add(1, frontAnimationImageView);
    }

    String frontAnimationUrl = "file:" + frontAnimationFilePathLabel.getText();
    String backAnimationUrl = "file:" + backAnimationFilePathLabel.getText();
    Image frontAnimationImage = new Image(frontAnimationUrl);
    Image backAnimationImage = new Image(backAnimationUrl);
    
    /**
     * 常時アニメーション(前面)
     */
    // ObservableList<Node> stackPaneNodes = stackPane.getChildren();
    if (frontAnimationIsOnCheckBox.isSelected() == true && frontAnimationImage.errorProperty().get() == false && frontAnimationDisplaysOnSpecificTimeCheckBox.isSelected() == false) {
      frontAnimationImageView.setImage(frontAnimationImage);
    }
    /**
     * 常時アニメーション(背面)
     */
    if (backAnimationIsOnCheckBox.isSelected() == true && backAnimationImage.errorProperty().get() == false && backAnimationDisplaysOnSpecificTimeCheckBox.isSelected() == false) {
      backAnimationImageView.setImage(backAnimationImage);
    }

    // 指定時アニメーションを表示しない場合はここで処理が終了
    if (frontAnimationDisplaysOnSpecificTimeCheckBox.isSelected() == false && backAnimationDisplaysOnSpecificTimeCheckBox.isSelected() == false) {
      return;
    }

    /**
     * 指定時アニメーション
     */

    // 指定時アニメーションの有効な表示時刻を取得
    List<LocalDateTime> specificDateTimeList = new ArrayList<LocalDateTime>();
    List<AnimationType> specificAnimationTypeList = new ArrayList<AnimationType>();
    // アニメーション(前面)の表示時刻
    if (frontAnimationIsOnCheckBox.isSelected() == true && frontAnimationImage.errorProperty().get() == false && frontAnimationDisplaysOnSpecificTimeCheckBox.isSelected() == true) {
      if (specificFrontAnimationIsOnCheckBox1.isSelected() == true && 
      specificFrontAnimationDatePicker1.getValue() != null && 
      specificFrontAnimationHourChoiceBox1.getValue() != null && 
      specificFrontAnimationMinuteChoiceBox1.getValue() != null && 
      specificFrontAnimationSecondChoiceBox1.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificFrontAnimationDatePicker1.getValue(), 
          LocalTime.of(specificFrontAnimationHourChoiceBox1.getValue(), specificFrontAnimationMinuteChoiceBox1.getValue(), specificFrontAnimationSecondChoiceBox1.getValue())
        ));

        specificAnimationTypeList.add(AnimationType.Front);
      }
      if (specificFrontAnimationIsOnCheckBox2.isSelected() == true && 
      specificFrontAnimationDatePicker2.getValue() != null && 
      specificFrontAnimationHourChoiceBox2.getValue() != null && 
      specificFrontAnimationMinuteChoiceBox2.getValue() != null && 
      specificFrontAnimationSecondChoiceBox2.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificFrontAnimationDatePicker2.getValue(), 
          LocalTime.of(specificFrontAnimationHourChoiceBox2.getValue(), specificFrontAnimationMinuteChoiceBox2.getValue(), specificFrontAnimationSecondChoiceBox2.getValue())
        ));
        
        specificAnimationTypeList.add(AnimationType.Front);
      }
      if (specificFrontAnimationIsOnCheckBox3.isSelected() == true && 
      specificFrontAnimationDatePicker3.getValue() != null && 
      specificFrontAnimationHourChoiceBox3.getValue() != null && 
      specificFrontAnimationMinuteChoiceBox3.getValue() != null && 
      specificFrontAnimationSecondChoiceBox3.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificFrontAnimationDatePicker3.getValue(), 
          LocalTime.of(specificFrontAnimationHourChoiceBox3.getValue(), specificFrontAnimationMinuteChoiceBox3.getValue(), specificFrontAnimationSecondChoiceBox3.getValue())
        ));
        
        specificAnimationTypeList.add(AnimationType.Front);
      }
      if (specificFrontAnimationIsOnCheckBox4.isSelected() == true && 
      specificFrontAnimationDatePicker4.getValue() != null && 
      specificFrontAnimationHourChoiceBox4.getValue() != null && 
      specificFrontAnimationMinuteChoiceBox4.getValue() != null && 
      specificFrontAnimationSecondChoiceBox4.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificFrontAnimationDatePicker4.getValue(), 
          LocalTime.of(specificFrontAnimationHourChoiceBox4.getValue(), specificFrontAnimationMinuteChoiceBox4.getValue(), specificFrontAnimationSecondChoiceBox4.getValue())
        ));
        
        specificAnimationTypeList.add(AnimationType.Front);
      }
    }

    // アニメーション(背面)の表示時刻
    if (backAnimationIsOnCheckBox.isSelected() == true && backAnimationImage.errorProperty().get() == false && backAnimationDisplaysOnSpecificTimeCheckBox.isSelected() == true) {
      if (specificBackAnimationIsOnCheckBox1.isSelected() == true && 
      specificBackAnimationDatePicker1.getValue() != null && 
      specificBackAnimationHourChoiceBox1.getValue() != null && 
      specificBackAnimationMinuteChoiceBox1.getValue() != null && 
      specificBackAnimationSecondChoiceBox1.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificBackAnimationDatePicker1.getValue(), 
          LocalTime.of(specificBackAnimationHourChoiceBox1.getValue(), specificBackAnimationMinuteChoiceBox1.getValue(), specificBackAnimationSecondChoiceBox1.getValue())
        ));
        
        specificAnimationTypeList.add(AnimationType.Back);
      }
      if (specificBackAnimationIsOnCheckBox2.isSelected() == true && 
      specificBackAnimationDatePicker2.getValue() != null && 
      specificBackAnimationHourChoiceBox2.getValue() != null && 
      specificBackAnimationMinuteChoiceBox2.getValue() != null && 
      specificBackAnimationSecondChoiceBox2.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificBackAnimationDatePicker2.getValue(), 
          LocalTime.of(specificBackAnimationHourChoiceBox2.getValue(), specificBackAnimationMinuteChoiceBox2.getValue(), specificBackAnimationSecondChoiceBox2.getValue())
        ));
        
        specificAnimationTypeList.add(AnimationType.Back);
      }
      if (specificBackAnimationIsOnCheckBox3.isSelected() == true && 
      specificBackAnimationDatePicker3.getValue() != null && 
      specificBackAnimationHourChoiceBox3.getValue() != null && 
      specificBackAnimationMinuteChoiceBox3.getValue() != null && 
      specificBackAnimationSecondChoiceBox3.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificBackAnimationDatePicker3.getValue(), 
          LocalTime.of(specificBackAnimationHourChoiceBox3.getValue(), specificBackAnimationMinuteChoiceBox3.getValue(), specificBackAnimationSecondChoiceBox3.getValue())
        ));

        specificAnimationTypeList.add(AnimationType.Back);
      }
      if (specificBackAnimationIsOnCheckBox4.isSelected() == true && 
      specificBackAnimationDatePicker4.getValue() != null && 
      specificBackAnimationHourChoiceBox4.getValue() != null && 
      specificBackAnimationMinuteChoiceBox4.getValue() != null && 
      specificBackAnimationSecondChoiceBox4.getValue() != null) {
        specificDateTimeList.add(LocalDateTime.of(
          specificBackAnimationDatePicker4.getValue(), 
          LocalTime.of(specificBackAnimationHourChoiceBox4.getValue(), specificBackAnimationMinuteChoiceBox4.getValue(), specificBackAnimationSecondChoiceBox4.getValue())
        ));
        
        specificAnimationTypeList.add(AnimationType.Back);
      }
    }

    // 有効な表示時刻が存在しない場合はここで処理を終了
    if (specificDateTimeList.size() == 0) {
      return;
    }

    // animationTimelineの定義
    animationTimeline = new Timeline(
      new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          LocalDateTime nowDetail = LocalDateTime.now();
          LocalDateTime now = LocalDateTime.of(
            nowDetail.toLocalDate(),
            LocalTime.of(nowDetail.getHour(), nowDetail.getMinute(), nowDetail.getSecond())
          );
          System.out.println(now);

          for (int i = 0; i < specificDateTimeList.size(); i++) {
            if (now.equals(specificDateTimeList.get(i)) == true) {
              switch (specificAnimationTypeList.get(i)) {
                case Front:
                  Image frontAnimationImage = new Image(frontAnimationUrl, true);
                  frontAnimationImageView.setImage(frontAnimationImage);
                  System.out.println("前面セット");
                  break;
                case Back:
                  Image backAnimationImage = new Image(backAnimationUrl, true);
                  backAnimationImageView.setImage(backAnimationImage);
                  System.out.println("背面セット");
                  break;
              }
            }
          }
        }
      })
    );

    // animationTimelineの開始
    animationTimeline.setCycleCount(Timeline.INDEFINITE);
    animationTimeline.play();
  }

  private enum AnimationType {
    Front, Back;
  }

  /**
   * 新規ウィンドウで時計を表示・更新ボタンが押下された場合の処理
   */
  @FXML protected void onDisplayClockButtonClicked() {
    // メイン画面を表示するStageとStackPaneがnullである場合(=初回)のみ新規ウィンドウで開く
    if (mainStage == null && mainScene == null && mainStackPane == null) {
      // 透明なStageのインスタンスを生成
      mainStage = new Stage(StageStyle.TRANSPARENT);

      // StackPaneの生成
      mainStackPane = new StackPane();

      // StackPaneのスタイルを変更
      mainStackPane.setStyle(
        "-fx-background-radius: 10; -fx-background-color: transparent;"  // 本番用
        // "-fx-background-radius: 10; -fx-background-color: rgba(0,0,0,0.3);" // テスト用
      );

      // StackPane上でマウスが押された場合はクリックされた時点の座標を取得
      mainStackPane.setOnMousePressed((MouseEvent event) -> {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
      });
      // StackPane上でドラッグされた場合は移動距離に応じてStageを移動させる
      mainStackPane.setOnMouseDragged((MouseEvent event) -> {
        // Stageの座標を移動
        mainStage.setX(event.getScreenX() - xOffset);
        mainStage.setY(event.getScreenY() - yOffset);
      });

      // SceneのサイズをSpinnerから取得
      int width = widthSpinner.getValue();
      int height = heightSpinner.getValue();

      // SceneにStackPaneをセット
      mainScene = new Scene(mainStackPane, width, height);
      
      // Sceneの背景色をなしにする
      mainScene.setFill(null);
      
      // StageにSceneをセット
      mainStage.setScene(mainScene);

      /**
       * Stage ↔ Scene ↔ StackPane 間でのサイズ連動
       */
      // Scene → StackPane
      mainScene.widthProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
          mainStackPane.setPrefWidth(newSceneWidth.doubleValue());
        }
      });
      mainScene.heightProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
          mainStackPane.setPrefHeight(newSceneHeight.doubleValue());
        }
      });
      // Scene → Stage
      mainScene.widthProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
          mainStackPane.setPrefWidth(newSceneWidth.doubleValue());
        }
      });
      mainScene.heightProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
          mainStackPane.setPrefHeight(newSceneHeight.doubleValue());
        }
      });

      // Stageのウィンドウリサイズを可能にする
      mainStage.setResizable(true);

      // Stageを常に最前面に表示する
      mainStage.setAlwaysOnTop(true);

      // Stageのウィンドウタイトル
      mainStage.setTitle("Customizable Clock for Sealily");

      // Stageの表示
      mainStage.show();
    }

    // StackPaneの初期化
    mainStackPane.getChildren().clear();

    /**
     * ImageView(アナログ時計), Label(デジタル時計/タイマー)のStackPaneへの反映
     */
    // アナログ時計
    if (analogClockModeRadioButton.isSelected() == true) {
      // デジタル時計(タイマー)で利用するTimelineのstop
      if (mainDigitalLabelTimeline != null) {
        mainDigitalLabelTimeline.stop();
        mainDigitalLabelTimeline = null;
      }

      // StackPaneへのアナログ時計gifの追加
      this.attachImageViewToStackPane(mainStackPane, PreviewType.Main, mainHourRotateTransition, mainMinRotateTransition, mainSecRotateTransition);
    }
    // デジタル時計(タイマー)
    else {
      // アナログ時計で利用するRotateTransitionのstop
      if (mainHourRotateTransition != null) {
        mainHourRotateTransition.stop();
        mainHourRotateTransition = null;
      }
      if (mainMinRotateTransition != null) {
        mainMinRotateTransition.stop();
        mainMinRotateTransition = null;
      }
      if (mainSecRotateTransition != null) {
        mainSecRotateTransition.stop();
        mainSecRotateTransition = null;
      }

      /**
       * 各種情報の取得
       */
      if (timerDatePicker.getValue() != null && timerHourChoiceBox.getValue() != null && timerMinuteChoiceBox.getValue() != null && timerSecondChoiceBox.getValue() != null) {
        mainLocalDateTime = LocalDateTime.of(
          timerDatePicker.getValue(), 
          LocalTime.of(
            timerHourChoiceBox.getValue(), 
            timerMinuteChoiceBox.getValue(), 
            timerSecondChoiceBox.getValue()
          )
        );
      }
      else {
        mainLocalDateTime = LocalDateTime.now().plusHours(1);
      }
      mainBeforeMin = beforeMinuteSpinner.getValue();
      mainAfterMin = afterMinuteSpinner.getValue();
      mainBaseFontColor = baseFontColorPicker.getValue();
      mainBeforeFontColor = beforeFontColorPicker.getValue();
      mainAfterFontColor = afterFontColorPicker.getValue();

      // StackPaneへのLabelの追加
      this.attachLabelToStackPane(mainStackPane, mainDigitalLabelTimeline, mainLocalDateTime, mainBeforeMin, mainAfterMin, mainBaseFontColor, mainBeforeFontColor, mainAfterFontColor);
    }

    // StackPaneへのアニメーションの追加
    this.addAnimationsOntoStackPane(mainStackPane, mainAnimationTimeline);
  }
}
