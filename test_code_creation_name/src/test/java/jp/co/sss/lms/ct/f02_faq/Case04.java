package jp.co.sss.lms.ct.f02_faq;

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

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

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
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() throws Exception {
		// ID入力
		webDriver.findElement(By.cssSelector("input[type='text']")).sendKeys("StudentAA02");
		// パスワード入力
		webDriver.findElement(By.cssSelector("input[type='password']")).sendKeys("StudentAA2");
		// ログインボタン押下
		webDriver.findElement(By.cssSelector(".btn-primary")).click();
		//画面遷移後のスクリーンショット
		visibilityTimeout(By.className("active"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() throws Exception {
		//「機能」プルダウンを押下
		webDriver.findElement(By.cssSelector(".dropdown-toggle")).click();
		//「ヘルプ」を押下
		webDriver.findElement(By.cssSelector("a[href='/lms/help']")).click();
		//画面遷移後のスクリーンショット
		visibilityTimeout(By.tagName("h4"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("ヘルプ | LMS", webDriver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() throws Exception {
		// 「よくある質問」を押下
		webDriver.findElement(By.cssSelector("a[href='/lms/faq']")).click();
		//別タブで表示
		Object[] windowHandles = webDriver.getWindowHandles().toArray();
		webDriver.switchTo().window((String) windowHandles[1]);
		//画面遷移後のスクリーンショット
		visibilityTimeout(By.tagName("h2"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("よくある質問 | LMS", webDriver.getTitle());
	}

}
