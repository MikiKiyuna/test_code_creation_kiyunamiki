package jp.co.sss.lms.ct.f03_report;

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
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() throws Exception {
		scrollBy("100");
		// 「詳細」ボタンを押下
		webDriver
				.findElement(By.xpath(
						"//form[.//input[@name='sectionId' and @value='4']]//input[@type='submit' and @value='詳細']"))
				.click();
		//スクリーンショットの取得
		visibilityTimeout(By.className("active"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() throws Exception {
		//「提出済み日報【デモ】を確認する」ボタンを押下
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();
		//スクリーンショットの取得
		visibilityTimeout(By.cssSelector(".well.bs-component"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() throws Exception {

		WebElement content = webDriver.findElement(By.id("content_0"));
		//既存の報告内容を削除
		content.clear();
		//新しい報告内容
		content.sendKeys("修正後のテスト用レポートです。");
		//「提出する」ボタンを押下
		webDriver.findElement(By.cssSelector("button[class='btn btn-primary']")).click();
		//スクリーンショットの取得
		visibilityTimeout(By.className("table"), 5);
		getEvidence(new Object() {
		});
		//値の取得
		String value = webDriver.findElement(By.cssSelector("input[type='submit']")).getAttribute("value");
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() throws Exception {
		// リンクを押下する。
		webDriver.findElement(
				By.cssSelector("a[href='/lms/user/detail']")).click();
		//スクリーンショットの取得
		visibilityTimeout(By.className("table"), 5);
		scrollBy("100");
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("ユーザー詳細", webDriver.getTitle());
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() throws Exception {
		//「ユーザー詳細画面」を押下
		webDriver.findElement(
				By.cssSelector("form[action='/lms/report/detail'] input[type='submit']")).click();
		//スクリーンショットの取得
		visibilityTimeout(By.className("table"), 5);
		getEvidence(new Object() {
		});

		//値の取得
		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

	}

}
