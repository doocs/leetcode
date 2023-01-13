# [2288. 价格减免](https://leetcode.cn/problems/apply-discount-to-prices)

[English Version](/solution/2200-2299/2288.Apply%20Discount%20to%20Prices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>句子</strong> 是由若干个单词组成的字符串，单词之间用单个空格分隔，其中每个单词可以包含数字、小写字母、和美元符号 <code>'$'</code> 。如果单词的形式为美元符号后跟着一个非负实数，那么这个单词就表示一个价格。</p>

<ul>
	<li>例如 <code>"$100"</code>、<code>"$23"</code> 和 <code>"$6.75"</code> 表示价格，而 <code>"100"</code>、<code>"$"</code> 和 <code>"2$3"</code> 不是。</li>
</ul>

<p><strong>注意：</strong>本题输入中的价格均为整数。</p>

<p>给你一个字符串 <code>sentence</code>&nbsp; 和一个整数 <code>discount</code> 。对于每个表示价格的单词，都在价格的基础上减免 <code>discount%</code> ，并 <strong>更新</strong> 该单词到句子中。所有更新后的价格应该表示为一个 <strong>恰好保留小数点后两位</strong> 的数字。</p>

<p>返回表示修改后句子的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "there are $1 $2 and 5$ candies in the shop", discount = 50
<strong>输出：</strong>"there are $0.50 $1.00 and 5$ candies in the shop"
<strong>解释：</strong>
表示价格的单词是 "$1" 和 "$2" 。 
- "$1" 减免 50% 为 "$0.50" ，所以 "$1" 替换为 "$0.50" 。
- "$2" 减免 50% 为 "$1" ，所以 "$1" 替换为 "$1.00" 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "1 2 $3 4 $5 $6 7 8$ $9 $10$", discount = 100
<strong>输出：</strong>"1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$"
<strong>解释：</strong>
任何价格减免 100% 都会得到 0 。
表示价格的单词分别是 "$3"、"$5"、"$6" 和 "$9"。
每个单词都替换为 "$0.00"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 10<sup>5</sup></code></li>
	<li><code>sentence</code> 由小写英文字母、数字、<code>' '</code> 和&nbsp;<code>'$'</code> 组成</li>
	<li><code>sentence</code> 不含前导和尾随空格</li>
	<li><code>sentence</code> 的所有单词都用单个空格分隔</li>
	<li>所有价格都是 <strong>正</strong> 整数且不含前导零</li>
	<li>所有价格 <strong>最多</strong> 为&nbsp; <code>10</code> 位数字</li>
	<li><code>0 &lt;= discount &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们可以将句子按空格分割成单词数组，然后遍历单词数组，对于每个单词，如果其表示价格，则将其更新为减免折扣后的价格。最后将更新后的单词数组拼接成以空格分隔的字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `sentence` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def discountPrices(self, sentence: str, discount: int) -> str:
        ans = []
        for w in sentence.split():
            if w[0] == '$' and w[1:].isdigit():
                w = f'${int(w[1:]) * (1 - discount / 100):.2f}'
            ans.append(w)
        return ' '.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; ++i) {
            if (check(words[i])) {
                double t = Long.parseLong(words[i].substring(1)) * (1 - discount / 100.0);
                words[i] = String.format("$%.2f", t);
            }
        }
        return String.join(" ", words);
    }

    private boolean check(String s) {
        if (s.charAt(0) != '$' || s.length() == 1) {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string discountPrices(string sentence, int discount) {
        istringstream is(sentence);
        string w;
        string ans;
        auto check = [](string s) {
            if (s[0] != '$' || s.size() == 1) {
                return false;
            }
            for (int i = 1; i < s.size(); ++i) {
                if (!isdigit(s[i])) {
                    return false;
                }
            }
            return true;
        };
        while (is >> w) {
            if (check(w)) {
                long long v = stoll(w.substr(1)) * (100 - discount);
                char t[20];
                sprintf(t, "$%lld.%02lld", v / 100, v % 100);
                ans += t;
            } else {
                ans += w;
            }
            ans += ' ';
        }
        ans.pop_back();
        return ans;
    }
};
```

### **Go**

```go
func discountPrices(sentence string, discount int) string {
	words := strings.Split(sentence, " ")
	for i, w := range words {
		if w[0] == '$' {
			if v, err := strconv.Atoi(w[1:]); err == nil {
				words[i] = fmt.Sprintf("$%.2f", float64(v*(100-discount))/100)
			}
		}
	}
	return strings.Join(words, " ")
}
```

### **TypeScript**

```ts
function discountPrices(sentence: string, discount: number): string {
    const sell = (100 - discount) / 100;
    let reg = new RegExp(/^(\$)(([1-9]\d*\.?\d*)|(0\.\d*))$/g);
    let arr = sentence.split(' ').map(d => {
        if (!reg.test(d)) return d;
        return d.replace(reg, (s, $1, $2) => {
            return `$${(sell * $2).toFixed(2)}`;
        });
    });
    return arr.join(' ');
}
```

### **...**

```

```

<!-- tabs:end -->
