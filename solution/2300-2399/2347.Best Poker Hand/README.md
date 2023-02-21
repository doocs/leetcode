# [2347. 最好的扑克手牌](https://leetcode.cn/problems/best-poker-hand)

[English Version](/solution/2300-2399/2347.Best%20Poker%20Hand/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>ranks</code>&nbsp;和一个字符数组&nbsp;<code>suit</code>&nbsp;。你有&nbsp;<code>5</code>&nbsp;张扑克牌，第&nbsp;<code>i</code>&nbsp;张牌大小为&nbsp;<code>ranks[i]</code>&nbsp;，花色为&nbsp;<code>suits[i]</code>&nbsp;。</p>

<p>下述是从好到坏你可能持有的 <strong>手牌类型&nbsp;</strong>：</p>

<ol>
	<li><code>"Flush"</code>：同花，五张相同花色的扑克牌。</li>
	<li><code>"Three of a Kind"</code>：三条，有 3 张大小相同的扑克牌。</li>
	<li><code>"Pair"</code>：对子，两张大小一样的扑克牌。</li>
	<li><code>"High Card"</code>：高牌，五张大小互不相同的扑克牌。</li>
</ol>

<p>请你返回一个字符串，表示给定的 5 张牌中，你能组成的 <strong>最好手牌类型</strong>&nbsp;。</p>

<p><strong>注意：</strong>返回的字符串&nbsp;<strong>大小写</strong>&nbsp;需与题目描述相同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>ranks = [13,2,3,1,9], suits = ["a","a","a","a","a"]
<b>输出：</b>"Flush"
<b>解释：</b>5 张扑克牌的花色相同，所以返回 "Flush" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>ranks = [4,4,2,4,4], suits = ["d","a","a","b","c"]
<b>输出：</b>"Three of a Kind"
<b>解释：</b>第一、二和四张牌组成三张相同大小的扑克牌，所以得到 "Three of a Kind" 。
注意我们也可以得到 "Pair" ，但是 "Three of a Kind" 是更好的手牌类型。
有其他的 3 张牌也可以组成 "Three of a Kind" 手牌类型。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>ranks = [10,10,2,12,9], suits = ["a","b","c","a","d"]
<b>输出：</b>"Pair"
<b>解释：</b>第一和第二张牌大小相同，所以得到 "Pair" 。
我们无法得到 "Flush" 或者 "Three of a Kind" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>ranks.length == suits.length == 5</code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 13</code></li>
	<li><code>'a' &lt;= suits[i] &lt;= 'd'</code></li>
	<li>任意两张扑克牌不会同时有相同的大小和花色。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们可以先遍历数组 $suits$，判断相邻两个元素是否均相等，如果是，则返回 `"Flush"`。

接下来，我们用哈希表或数组 $cnt$ 统计每张牌的数量：

-   如果有任意一张牌的数量等于 $3$，返回 `"Three of a Kind"`；
-   否则，如果有任意一张牌的数量等于 $2$，返回 `"Pair"`；
-   否则，返回 `"High Card"`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $ranks$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def bestHand(self, ranks: List[int], suits: List[str]) -> str:
        # if len(set(suits)) == 1:
        if all(a == b for a, b in pairwise(suits)):
            return 'Flush'
        cnt = Counter(ranks)
        if any(v >= 3 for v in cnt.values()):
            return 'Three of a Kind'
        if any(v == 2 for v in cnt.values()):
            return 'Pair'
        return 'High Card'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        boolean flush = true;
        for (int i = 1; i < 5 && flush; ++i) {
            flush = suits[i] == suits[i - 1];
        }
        if (flush) {
            return "Flush";
        }
        int[] cnt = new int[14];
        boolean pair = false;
        for (int x : ranks) {
            if (++cnt[x] == 3) {
                return "Three of a Kind";
            }
            pair = pair || cnt[x] == 2;
        }
        return pair ? "Pair" : "High Card";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string bestHand(vector<int>& ranks, vector<char>& suits) {
        bool flush = true;
        for (int i = 1; i < 5 && flush; ++i) {
            flush = suits[i] == suits[i - 1];
        }
        if (flush) {
            return "Flush";
        }
        int cnt[14]{};
        bool pair = false;
        for (int& x : ranks) {
            if (++cnt[x] == 3) {
                return "Three of a Kind";
            }
            pair |= cnt[x] == 2;
        }
        return pair ? "Pair" : "High Card";
    }
};
```

### **Go**

```go
func bestHand(ranks []int, suits []byte) string {
	flush := true
	for i := 1; i < 5 && flush; i++ {
		flush = suits[i] == suits[i-1]
	}
	if flush {
		return "Flush"
	}
	cnt := [14]int{}
	pair := false
	for _, x := range ranks {
		cnt[x]++
		if cnt[x] == 3 {
			return "Three of a Kind"
		}
		pair = pair || cnt[x] == 2
	}
	if pair {
		return "Pair"
	}
	return "High Card"
}
```

### **TypeScript**

```ts
function bestHand(ranks: number[], suits: string[]): string {
    if (suits.every(v => v === suits[0])) {
        return 'Flush';
    }
    const count = new Array(14).fill(0);
    let isPair = false;
    for (const v of ranks) {
        if (++count[v] === 3) {
            return 'Three of a Kind';
        }
        isPair = isPair || count[v] === 2;
    }
    if (isPair) {
        return 'Pair';
    }
    return 'High Card';
}
```

### **Rust**

```rust
impl Solution {
    pub fn best_hand(ranks: Vec<i32>, suits: Vec<char>) -> String {
        if suits.iter().all(|v| *v == suits[0]) {
            return "Flush".to_string();
        }
        let mut count = [0; 14];
        let mut is_pair = false;
        for &v in ranks.iter() {
            let i = v as usize;
            count[i] += 1;
            if count[i] == 3 {
                return "Three of a Kind".to_string();
            }
            is_pair = is_pair || count[i] == 2;
        }
        (if is_pair { "Pair" } else { "High Card" }).to_string()
    }
}
```

### **C**

```c
char *bestHand(int *ranks, int ranksSize, char *suits, int suitsSize) {
    bool isFlush = true;
    for (int i = 1; i < suitsSize; i++) {
        if (suits[0] != suits[i]) {
            isFlush = false;
            break;
        }
    }
    if (isFlush) {
        return "Flush";
    }
    int count[14] = {0};
    bool isPair = false;
    for (int i = 0; i < ranksSize; i++) {
        if (++count[ranks[i]] == 3) {
            return "Three of a Kind";
        }
        isPair = isPair || count[ranks[i]] == 2;
    }
    if (isPair) {
        return "Pair";
    }
    return "High Card";
}
```

### **...**

```

```

<!-- tabs:end -->
