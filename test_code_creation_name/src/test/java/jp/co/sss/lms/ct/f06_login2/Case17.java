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
 * ケース17
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース17 受講生 初回ログイン 正常系")
public class Case17 {

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
		webDriver.findElement(By.cssSelector("input[type='text']")).sendKeys("StudentAB01");
		// パスワード入力
		webDriver.findElement(By.cssSelector("input[type='password']")).sendKeys("StudentAB01");
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
	@DisplayName("テスト04 変更パスワードを入力し「変更」ボタン押下")
	void test04() throws Exception {
		// 現在のパスワードを入力
		webDriver.findElement(By.id("currentPassword")).sendKeys("StudentAB01");
		//新しいパスワードを入力
		webDriver.findElement(By.id("password")).sendKeys("StudentAB1");
		//確認パスワードを入力
		webDriver.findElement(By.id("passwordConfirm")).sendKeys("StudentAB1");

		// 「変更」ボタンを押下する
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		//ダイアログ表示まで待機
		visibilityTimeout(By.id("upd-btn"), 5);
		//確認ダイアログの「変更」ボタンを押下
		webDriver.findElement(By.id("upd-btn")).click();
		//スクリーンショット取得
		Thread.sleep(3000);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

	}

}
