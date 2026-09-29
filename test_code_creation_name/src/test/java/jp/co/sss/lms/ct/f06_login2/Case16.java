package jp.co.sss.lms.ct.f06_login2;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能②
 * ケース16
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース16 受講生 初回ログイン 変更パスワード未入力")
public class Case16 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() throws Exception {
		// URLに遷移
		goTo("http://localhost:8080/lms");
		//画面表示待ち
		Thread.sleep(3000);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("ログイン | LMS", webDriver.getTitle());
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() throws Exception {
		// ID入力
		webDriver.findElement(By.cssSelector("input[type='text']")).sendKeys("StudentAA03");
		// パスワード入力
		webDriver.findElement(By.cssSelector("input[type='password']")).sendKeys("StudentAA03");
		// ログインボタン押下
		webDriver.findElement(By.cssSelector(".btn-primary")).click();
		//画面遷移後のスクリーンショット
		visibilityTimeout(By.cssSelector(".well.bs-component"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("セキュリティ規約 | LMS", webDriver.getTitle());

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() throws Exception {
		// 「同意します」にチェックを入れる
		final WebElement checkbox = webDriver.findElement(By.cssSelector("input[type='checkbox']"));
		checkbox.click();
		visibilityTimeout(By.cssSelector(".well.bs-component"), 5);
		// 「次へ」ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		//画面遷移後のスクリーンショット
		visibilityTimeout(By.cssSelector(".well.bs-component"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 パスワードを未入力で「変更」ボタン押下")
	void test04() throws Exception {
		// 「変更」ボタンを押下する
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		//ダイアログ表示まで待機
		visibilityTimeout(By.id("upd-btn"), 5);
		//確認ダイアログの「変更」ボタンを押下
		webDriver.findElement(By.id("upd-btn")).click();
		scrollBy("50");
		//エラーメッセージの表示
		visibilityTimeout(By.cssSelector(".well.bs-component"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		// 現在のパスワード
		WebElement currentPasswordError = webDriver
				.findElement(By.cssSelector("#currentPassword ~ ul .help-inline.error"));
		// 新しいパスワード
		WebElement passwordError = webDriver.findElement(By.cssSelector("#password ~ ul .help-inline.error"));
		// 確認パスワード
		WebElement passwordConfirmError = webDriver
				.findElement(By.cssSelector("#passwordConfirm ~ ul .help-inline.error"));

		assertEquals("現在のパスワードは必須です。", currentPasswordError.getText());
		assertTrue(passwordError.getText().contains("パスワードは必須です。"));
		assertEquals("確認パスワードは必須です。", passwordConfirmError.getText());
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 20文字以上の変更パスワードを入力し「変更」ボタン押下")
	void test05() throws Exception {
		//前の入力値の削除
		webDriver.findElement(By.id("currentPassword")).clear();
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("passwordConfirm")).clear();

		scrollBy("100");

		// 現在のパスワードを入力
		webDriver.findElement(By.id("currentPassword")).sendKeys("StudentAA03");
		//新しいパスワードを入力
		webDriver.findElement(By.id("password")).sendKeys("StudentLoginABCDEF003");
		//確認パスワードを入力
		webDriver.findElement(By.id("passwordConfirm")).sendKeys("StudentLoginABCDEF003");

		// 「変更」ボタンを押下する
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		//ダイアログ表示まで待機
		visibilityTimeout(By.id("upd-btn"), 5);
		//確認ダイアログの「変更」ボタンを押下
		webDriver.findElement(By.id("upd-btn")).click();
		//エラーメッセージの表示
		visibilityTimeout(By.cssSelector(".help-inline.error"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		WebElement errorMessage = webDriver.findElement(By.cssSelector("#password ~ ul .help-inline.error"));
		assertEquals("パスワードの長さが最大値(20)を超えています。", errorMessage.getText());
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 ポリシーに合わない変更パスワードを入力し「変更」ボタン押下")
	void test06() throws Exception {
		//前の入力値の削除
		webDriver.findElement(By.id("currentPassword")).clear();
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("passwordConfirm")).clear();

		// 現在のパスワードを入力
		webDriver.findElement(By.id("currentPassword")).sendKeys("StudentAA03");
		//新しいパスワードを入力
		webDriver.findElement(By.id("password")).sendKeys("Student@#03");
		//確認パスワードを入力
		webDriver.findElement(By.id("passwordConfirm")).sendKeys("Student@#03");

		// 「変更」ボタンを押下する
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		//ダイアログ表示まで待機
		visibilityTimeout(By.id("upd-btn"), 5);
		//確認ダイアログの「変更」ボタンを押下
		webDriver.findElement(By.id("upd-btn")).click();
		//エラーメッセージの表示
		visibilityTimeout(By.cssSelector(".help-inline.error"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		WebElement errorMessage = webDriver.findElement(By.cssSelector("#password ~ ul .help-inline.error"));
		assertEquals("「パスワード」には半角英数字のみ使用可能です。また、半角英大文字、半角英小文字、数字を含めた8～20文字を入力してください。", errorMessage.getText());
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 一致しない確認パスワードを入力し「変更」ボタン押下")
	void test07() throws Exception {
		//前の入力値の削除
		webDriver.findElement(By.id("currentPassword")).clear();
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("passwordConfirm")).clear();

		// 現在のパスワードを入力
		webDriver.findElement(By.id("currentPassword")).sendKeys("StudentAA03");
		//新しいパスワードを入力
		webDriver.findElement(By.id("password")).sendKeys("StudentAA3");
		//確認パスワードを入力
		webDriver.findElement(By.id("passwordConfirm")).sendKeys("StudentAAA3");

		// 「変更」ボタンを押下する
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		//ダイアログ表示まで待機
		visibilityTimeout(By.id("upd-btn"), 5);
		//確認ダイアログの「変更」ボタンを押下
		webDriver.findElement(By.id("upd-btn")).click();
		//エラーメッセージの表示
		visibilityTimeout(By.cssSelector(".help-inline.error"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		WebElement errorMessage = webDriver.findElement(By.cssSelector("#password ~ ul .help-inline.error"));
		assertEquals("パスワードと確認パスワードが一致しません。", errorMessage.getText());
	}

}
