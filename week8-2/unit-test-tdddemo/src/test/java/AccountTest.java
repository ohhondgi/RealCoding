import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    //계정 생성 확인 테스트
    @Test
    public void 계정생성확인초기금액이0인지테스트(){
        Account account = new Account();
        int balance = account.getBalance();
        System.out.println("balance : " + balance);
        assertThat(balance,is(0));
    }

    //계정에 예금후 잔액 확인 테스트
    @Test
    public void 입금후잔액테스트(){
        Account account = new Account();
        account.deposit(1000);
        System.out.println("balance: " + account.getBalance());
        assertThat(account.getBalance(), is(1000));
    }

    //계정에 출금후 잔액 확인 테스트
    @Test
    public void 출금후잔액테스트(){
        Account account = new Account();
        account.withdraw(1000);
        assertThat(account.getBalance(), is(0));
    }

    //계정에 잔액보다 출금액이 크면 출금되지 않고 잔액이 그대로인지 확인 테스트
    @Test
    public void 계정잔액보다출금이크면안한다(){
        Account account = new Account();
        account.deposit(300);
        account.withdraw(700);
        assertThat(account.getBalance(), is(300));
    }
}
