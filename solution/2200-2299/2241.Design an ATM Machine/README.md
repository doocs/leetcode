# [2241. 设计一个 ATM 机器](https://leetcode.cn/problems/design-an-atm-machine)

[English Version](/solution/2200-2299/2241.Design%20an%20ATM%20Machine/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 ATM 机器，存有&nbsp;<code>5</code>&nbsp;种面值的钞票：<code>20</code>&nbsp;，<code>50</code>&nbsp;，<code>100</code>&nbsp;，<code>200</code>&nbsp;和&nbsp;<code>500</code>&nbsp;美元。初始时，ATM 机是空的。用户可以用它存或者取任意数目的钱。</p>

<p>取款时，机器会优先取 <b>较大</b>&nbsp;数额的钱。</p>

<ul>
	<li>比方说，你想取&nbsp;<code>$300</code>&nbsp;，并且机器里有&nbsp;<code>2</code>&nbsp;张 <code>$50</code>&nbsp;的钞票，<code>1</code>&nbsp;张&nbsp;<code>$100</code>&nbsp;的钞票和<code>1</code>&nbsp;张&nbsp;<code>$200</code>&nbsp;的钞票，那么机器会取出&nbsp;<code>$100</code> 和&nbsp;<code>$200</code>&nbsp;的钞票。</li>
	<li>但是，如果你想取&nbsp;<code>$600</code>&nbsp;，机器里有&nbsp;<code>3</code>&nbsp;张&nbsp;<code>$200</code>&nbsp;的钞票和<code>1</code>&nbsp;张&nbsp;<code>$500</code>&nbsp;的钞票，那么取款请求会被拒绝，因为机器会先取出&nbsp;<code>$500</code>&nbsp;的钞票，然后无法取出剩余的&nbsp;<code>$100</code>&nbsp;。注意，因为有&nbsp;<code>$500</code>&nbsp;钞票的存在，机器&nbsp;<strong>不能</strong>&nbsp;取&nbsp;<code>$200</code>&nbsp;的钞票。</li>
</ul>

<p>请你实现 ATM 类：</p>

<ul>
	<li><code>ATM()</code>&nbsp;初始化 ATM 对象。</li>
	<li><code>void deposit(int[] banknotesCount)</code>&nbsp;分别存入&nbsp;<code>$20</code>&nbsp;，<code>$50</code>，<code>$100</code>，<code>$200</code>&nbsp;和&nbsp;<code>$500</code>&nbsp;钞票的数目。</li>
	<li><code>int[] withdraw(int amount)</code>&nbsp;返回一个长度为&nbsp;<code>5</code>&nbsp;的数组，分别表示&nbsp;<code>$20</code>&nbsp;，<code>$50</code>，<code>$100</code>&nbsp;，<code>$200</code>&nbsp;和&nbsp;<code>$500</code>&nbsp;钞票的数目，并且更新 ATM 机里取款后钞票的剩余数量。如果无法取出指定数额的钱，请返回&nbsp;<code>[-1]</code>&nbsp;（这种情况下 <strong>不</strong>&nbsp;取出任何钞票）。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
[[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
<strong>输出：</strong>
[null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]

<strong>解释：</strong>
ATM atm = new ATM();
atm.deposit([0,0,1,2,1]); // 存入 1 张 $100 ，2 张 $200 和 1 张 $500 的钞票。
atm.withdraw(600);        // 返回 [0,0,1,0,1] 。机器返回 1 张 $100 和 1 张 $500 的钞票。机器里剩余钞票的数量为 [0,0,0,2,0] 。
atm.deposit([0,1,0,1,1]); // 存入 1 张 $50 ，1 张 $200 和 1 张 $500 的钞票。
                          // 机器中剩余钞票数量为 [0,1,0,3,1] 。
atm.withdraw(600);        // 返回 [-1] 。机器会尝试取出 $500 的钞票，然后无法得到剩余的 $100 ，所以取款请求会被拒绝。
                          // 由于请求被拒绝，机器中钞票的数量不会发生改变。
atm.withdraw(550);        // 返回 [0,1,0,0,1] ，机器会返回 1 张 $50 的钞票和 1 张 $500 的钞票。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>banknotesCount.length == 5</code></li>
	<li><code>0 &lt;= banknotesCount[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= amount &lt;= 10<sup>9</sup></code></li>
	<li><strong>总共</strong>&nbsp;最多有&nbsp;<code>5000</code>&nbsp;次&nbsp;<code>withdraw</code> 和&nbsp;<code>deposit</code>&nbsp;的调用。</li>
	<li><span style="">函数 </span><code>withdraw</code> 和&nbsp;<code>deposit</code>&nbsp;至少各有 <strong>一次&nbsp;</strong>调用。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们用一个数组 $d$ 记录钞票面额，用一个数组 $cnt$ 记录每种面额的钞票数量。

对于 `deposit` 操作，我们只需要将对应面额的钞票数量加上即可。时间复杂度 $O(1)$。

对于 `withdraw` 操作，我们从大到小枚举每种面额的钞票，取出尽可能多且不超过 $amount$ 的钞票，然后将 $amount$ 减去取出的钞票面额之和，如果最后 $amount$ 仍大于 $0$，说明无法取出 $amount$ 的钞票，返回 $-1$ 即可。否则，返回取出的钞票数量即可。时间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class ATM:
    def __init__(self):
        self.cnt = [0] * 5
        self.d = [20, 50, 100, 200, 500]

    def deposit(self, banknotesCount: List[int]) -> None:
        for i, v in enumerate(banknotesCount):
            self.cnt[i] += v

    def withdraw(self, amount: int) -> List[int]:
        ans = [0] * 5
        for i in range(4, -1, -1):
            ans[i] = min(amount // self.d[i], self.cnt[i])
            amount -= ans[i] * self.d[i]
        if amount > 0:
            return [-1]
        for i, v in enumerate(ans):
            self.cnt[i] -= v
        return ans


# Your ATM object will be instantiated and called as such:
# obj = ATM()
# obj.deposit(banknotesCount)
# param_2 = obj.withdraw(amount)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class ATM {
    private long[] cnt = new long[5];
    private int[] d = {20, 50, 100, 200, 500};

    public ATM() {
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; ++i) {
            cnt[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] ans = new int[5];
        for (int i = 4; i >= 0; --i) {
            ans[i] = (int) Math.min(amount / d[i], cnt[i]);
            amount -= ans[i] * d[i];
        }
        if (amount > 0) {
            return new int[] {-1};
        }
        for (int i = 0; i < 5; ++i) {
            cnt[i] -= ans[i];
        }
        return ans;
    }
}

/**
 * Your ATM object will be instantiated and called as such:
 * ATM obj = new ATM();
 * obj.deposit(banknotesCount);
 * int[] param_2 = obj.withdraw(amount);
 */
```

### **C++**

```cpp
class ATM {
public:
    ATM() {

    }

    void deposit(vector<int> banknotesCount) {
        for (int i = 0; i < banknotesCount.size(); ++i) {
            cnt[i] += banknotesCount[i];
        }
    }

    vector<int> withdraw(int amount) {
        vector<int> ans(5);
        for (int i = 4; ~i; --i) {
            ans[i] = min(1ll * amount / d[i], cnt[i]);
            amount -= ans[i] * d[i];
        }
        if (amount > 0) {
            return {-1};
        }
        for (int i = 0; i < 5; ++i) {
            cnt[i] -= ans[i];
        }
        return ans;
    }

private:
    long long cnt[5] = {0};
    int d[5] = {20, 50, 100, 200, 500};
};

/**
 * Your ATM object will be instantiated and called as such:
 * ATM* obj = new ATM();
 * obj->deposit(banknotesCount);
 * vector<int> param_2 = obj->withdraw(amount);
 */
```

### **Go**

```go
type ATM struct {
	d   [5]int
	cnt [5]int
}

func Constructor() ATM {
	return ATM{[5]int{20, 50, 100, 200, 500}, [5]int{}}
}

func (this *ATM) Deposit(banknotesCount []int) {
	for i, v := range banknotesCount {
		this.cnt[i] += v
	}
}

func (this *ATM) Withdraw(amount int) []int {
	ans := make([]int, 5)
	for i := 4; i >= 0; i-- {
		ans[i] = min(amount/this.d[i], this.cnt[i])
		amount -= ans[i] * this.d[i]
	}
	if amount > 0 {
		return []int{-1}
	}
	for i, v := range ans {
		this.cnt[i] -= v
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your ATM object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Deposit(banknotesCount);
 * param_2 := obj.Withdraw(amount);
 */
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
