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
 * ケース15
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース15 受講生 初回ログイン 利用規約に不同意")
public class Case15 {

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
	@DisplayName("テスト03 「同意します」チェックボックスにチェックをせず「次へ」ボタンを押下")
	void test03() {
		// 「次へ」ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		//エラーメッセージの表示
		visibilityTimeout(By.cssSelector(".error"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		WebElement errorMessage = webDriver.findElement(By.cssSelector(".error"));
		assertEquals("セキュリティ規約への同意は必須です。", errorMessage.getText());
	}

}
