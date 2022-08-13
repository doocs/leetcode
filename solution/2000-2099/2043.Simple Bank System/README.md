# [2043. 简易银行系统](https://leetcode.cn/problems/simple-bank-system)

[English Version](/solution/2000-2099/2043.Simple%20Bank%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。银行共有 <code>n</code> 个账户，编号从 <code>1</code> 到 <code>n</code> 。每个账号的初始余额存储在一个下标从 <strong>0</strong> 开始的整数数组 <code>balance</code>&nbsp;中，其中第 <code>(i + 1)</code> 个账户的初始余额是 <code>balance[i]</code> 。</p>

<p>请你执行所有 <strong>有效的</strong> 交易。如果满足下面全部条件，则交易 <strong>有效</strong> ：</p>

<ul>
	<li>指定的账户数量在 <code>1</code> 和 <code>n</code> 之间，且</li>
	<li>取款或者转账需要的钱的总数 <strong>小于或者等于</strong> 账户余额。</li>
</ul>

<p>实现 <code>Bank</code> 类：</p>

<ul>
	<li><code>Bank(long[] balance)</code> 使用下标从 <strong>0</strong> 开始的整数数组 <code>balance</code> 初始化该对象。</li>
	<li><code>boolean transfer(int account1, int account2, long money)</code> 从编号为&nbsp;<code>account1</code> 的账户向编号为 <code>account2</code> 的账户转帐 <code>money</code> 美元。如果交易成功，返回 <code>true</code> ，否则，返回 <code>false</code> 。</li>
	<li><code>boolean deposit(int account, long money)</code> 向编号为&nbsp;<code>account</code> 的账户存款 <code>money</code> 美元。如果交易成功，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
	<li><code>boolean withdraw(int account, long money)</code> 从编号为 <code>account</code> 的账户取款 <code>money</code> 美元。如果交易成功，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>：
["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
[[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
<strong>输出：</strong>
[null, true, true, true, false, false]

<strong>解释：</strong>
Bank bank = new Bank([10, 100, 20, 50, 30]);
bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
                         // 账户 3 余额为 $20 - $10 = $10 。
bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
                         // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 $20 。
                         // 账户 5 的余额为 $10 + $20 = $30 。
bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 $10 。
                         // 所以无法转账 $15 。
bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == balance.length</code></li>
	<li><code>1 &lt;= n, account, account1, account2 &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= balance[i], money &lt;= 10<sup>12</sup></code></li>
	<li><code>transfer</code>, <code>deposit</code>, <code>withdraw</code> 三个函数，<strong>每个</strong> 最多调用 <code>10<sup>4</sup></code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Bank:
    def __init__(self, balance: List[int]):
        self.balance = balance
        self.n = len(balance)

    def transfer(self, account1: int, account2: int, money: int) -> bool:
        if account1 > self.n or account2 > self.n or self.balance[account1 - 1] < money:
            return False
        self.balance[account1 - 1] -= money
        self.balance[account2 - 1] += money
        return True

    def deposit(self, account: int, money: int) -> bool:
        if account > self.n:
            return False
        self.balance[account - 1] += money
        return True

    def withdraw(self, account: int, money: int) -> bool:
        if account > self.n or self.balance[account - 1] < money:
            return False
        self.balance[account - 1] -= money
        return True


# Your Bank object will be instantiated and called as such:
# obj = Bank(balance)
# param_1 = obj.transfer(account1,account2,money)
# param_2 = obj.deposit(account,money)
# param_3 = obj.withdraw(account,money)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Bank {
    private long[] balance;
    private int n;

    public Bank(long[] balance) {
        this.balance = balance;
        this.n = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > n || account2 > n || balance[account1 - 1] < money) {
            return false;
        }
        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > n) {
            return false;
        }
        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > n || balance[account - 1] < money) {
            return false;
        }
        balance[account - 1] -= money;
        return true;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */
```

### **TypeScript**

```ts
class Bank {
    balance: number[];
    constructor(balance: number[]) {
        this.balance = balance;
    }

    transfer(account1: number, account2: number, money: number): boolean {
        if (
            account1 > this.balance.length ||
            account2 > this.balance.length ||
            money > this.balance[account1 - 1]
        )
            return false;
        this.balance[account1 - 1] -= money;
        this.balance[account2 - 1] += money;
        return true;
    }

    deposit(account: number, money: number): boolean {
        if (account > this.balance.length) return false;
        this.balance[account - 1] += money;
        return true;
    }

    withdraw(account: number, money: number): boolean {
        if (
            account > this.balance.length ||
            money > this.balance[account - 1]
        ) {
            return false;
        }
        this.balance[account - 1] -= money;
        return true;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * var obj = new Bank(balance)
 * var param_1 = obj.transfer(account1,account2,money)
 * var param_2 = obj.deposit(account,money)
 * var param_3 = obj.withdraw(account,money)
 */
```

### **C++**

```cpp
class Bank {
public:
    vector<long long> balance;
    int n;

    Bank(vector<long long>& balance) {
        this->balance = balance;
        n = balance.size();
    }

    bool transfer(int account1, int account2, long long money) {
        if (account1 > n || account2 > n || balance[account1 - 1] < money) return false;
        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }

    bool deposit(int account, long long money) {
        if (account > n) return false;
        balance[account - 1] += money;
        return true;
    }

    bool withdraw(int account, long long money) {
        if (account > n || balance[account - 1] < money) return false;
        balance[account - 1] -= money;
        return true;
    }
};

/**
 * Your Bank object will be instantiated and called as such:
 * Bank* obj = new Bank(balance);
 * bool param_1 = obj->transfer(account1,account2,money);
 * bool param_2 = obj->deposit(account,money);
 * bool param_3 = obj->withdraw(account,money);
 */
```

### **Go**

```go
type Bank struct {
	balance []int64
	n       int
}

func Constructor(balance []int64) Bank {
	return Bank{balance, len(balance)}
}

func (this *Bank) Transfer(account1 int, account2 int, money int64) bool {
	if account1 > this.n || account2 > this.n || this.balance[account1-1] < money {
		return false
	}
	this.balance[account1-1] -= money
	this.balance[account2-1] += money
	return true
}

func (this *Bank) Deposit(account int, money int64) bool {
	if account > this.n {
		return false
	}
	this.balance[account-1] += money
	return true
}

func (this *Bank) Withdraw(account int, money int64) bool {
	if account > this.n || this.balance[account-1] < money {
		return false
	}
	this.balance[account-1] -= money
	return true
}

/**
 * Your Bank object will be instantiated and called as such:
 * obj := Constructor(balance);
 * param_1 := obj.Transfer(account1,account2,money);
 * param_2 := obj.Deposit(account,money);
 * param_3 := obj.Withdraw(account,money);
 */
```

### **Rust**

```rust
struct Bank {
    balance: Vec<i64>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Bank {
    fn new(balance: Vec<i64>) -> Self {
        Bank { balance }
    }

    fn transfer(&mut self, account1: i32, account2: i32, money: i64) -> bool {
        let (account1, account2, n) = (account1 as usize, account2 as usize, self.balance.len());
        if n < account1 || n < account2 {
            return false;
        }
        if self.balance[account1 - 1] < money {
            return false;
        }
        self.balance[account1 - 1] -= money;
        self.balance[account2 - 1] += money;
        true
    }

    fn deposit(&mut self, account: i32, money: i64) -> bool {
        let (account,  n) = (account as usize, self.balance.len());
        if n < account {
            return false;
        }
        self.balance[account - 1] += money;
        true
    }

    fn withdraw(&mut self, account: i32, money: i64) -> bool {
        let (account,  n) = (account as usize, self.balance.len());
        if n < account {
            return false;
        }
        if self.balance[account - 1] < money {
            return false;
        }
        self.balance[account - 1] -= money;
        true
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * let obj = Bank::new(balance);
 * let ret_1: bool = obj.transfer(account1, account2, money);
 * let ret_2: bool = obj.deposit(account, money);
 * let ret_3: bool = obj.withdraw(account, money);
 */
```

### **...**

```

```

<!-- tabs:end -->
