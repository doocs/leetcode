# [2288. Apply Discount to Prices](https://leetcode.com/problems/apply-discount-to-prices)

[中文文档](/solution/2200-2299/2288.Apply%20Discount%20to%20Prices/README.md)

## Description

<p>A <strong>sentence</strong> is a string of single-space separated words where each word can contain digits, lowercase letters, and the dollar sign <code>&#39;$&#39;</code>. A word represents a <strong>price</strong> if it is a sequence of digits preceded by a dollar sign.</p>

<ul>
	<li>For example, <code>&quot;$100&quot;</code>, <code>&quot;$23&quot;</code>, and <code>&quot;$6&quot;</code> represent prices while <code>&quot;100&quot;</code>, <code>&quot;$&quot;</code>, and <code>&quot;$1e5&quot;</code> do not.</li>
</ul>

<p>You are given a string <code>sentence</code> representing a sentence and an integer <code>discount</code>. For each word representing a price, apply a discount of <code>discount%</code> on the price and <strong>update</strong> the word in the sentence. All updated prices should be represented with <strong>exactly two</strong> decimal places.</p>

<p>Return <em>a string representing the modified sentence</em>.</p>

<p>Note that all prices will contain <strong>at most</strong> <code>10</code> digits.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;there are $1 $2 and 5$ candies in the shop&quot;, discount = 50
<strong>Output:</strong> &quot;there are $0.50 $1.00 and 5$ candies in the shop&quot;
<strong>Explanation:</strong> 
The words which represent prices are &quot;$1&quot; and &quot;$2&quot;. 
- A 50% discount on &quot;$1&quot; yields &quot;$0.50&quot;, so &quot;$1&quot; is replaced by &quot;$0.50&quot;.
- A 50% discount on &quot;$2&quot; yields &quot;$1&quot;. Since we need to have exactly 2 decimal places after a price, we replace &quot;$2&quot; with &quot;$1.00&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;1 2 $3 4 $5 $6 7 8$ $9 $10$&quot;, discount = 100
<strong>Output:</strong> &quot;1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$&quot;
<strong>Explanation:</strong> 
Applying a 100% discount on any price will result in 0.
The words representing prices are &quot;$3&quot;, &quot;$5&quot;, &quot;$6&quot;, and &quot;$9&quot;.
Each of them is replaced by &quot;$0.00&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 10<sup>5</sup></code></li>
	<li><code>sentence</code> consists of lowercase English letters, digits, <code>&#39; &#39;</code>, and <code>&#39;$&#39;</code>.</li>
	<li><code>sentence</code> does not have leading or trailing spaces.</li>
	<li>All words in <code>sentence</code> are separated by a single space.</li>
	<li>All prices will be <strong>positive</strong> numbers without leading zeros.</li>
	<li>All prices will have <strong>at most</strong> <code>10</code> digits.</li>
	<li><code>0 &lt;= discount &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def discountPrices(self, sentence: str, discount: int) -> str:
        ans = []
        for w in sentence.split():
            if w[0] == '$' and w[1:].isdigit():
                t = int(w[1:]) * (1 - discount / 100)
                ans.append('$' + '{:.2f}'.format(t))
            else:
                ans.append(w)
        return ' '.join(ans)
```

### **Java**

```java
class Solution {
    public String discountPrices(String sentence, int discount) {
        List<String> ans = new ArrayList<>();
        for (String w : sentence.split(" ")) {
            if (w.charAt(0) == '$' && isNumber(w.substring(1))) {
                double t = Long.parseLong(w.substring(1)) * (1 - discount / 100.0);
                ans.add("$" + String.format("%.2f", t));
            } else {
                ans.add(w);
            }
        }
        return String.join(" ", ans);
    }

    private boolean isNumber(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
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
