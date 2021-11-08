# [299. 猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows)

[English Version](/solution/0200-0299/0299.Bulls%20and%20Cows/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你在和朋友一起玩 <a href="https://baike.baidu.com/item/%E7%8C%9C%E6%95%B0%E5%AD%97/83200?fromtitle=Bulls+and+Cows&amp;fromid=12003488&amp;fr=aladdin" target="_blank">猜数字（Bulls and Cows）</a>游戏，该游戏规则如下：</p>

<ol>
	<li>你写出一个秘密数字，并请朋友猜这个数字是多少。</li>
	<li>朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为&ldquo;Bulls&rdquo;, 公牛），有多少位属于数字猜对了但是位置不对（称为&ldquo;Cows&rdquo;, 奶牛）。</li>
	<li>朋友根据提示继续猜，直到猜出秘密数字。</li>
</ol>

<p>请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 <code>xAyB</code> ，<code>x</code> 和 <code>y</code> 都是数字，<code>A</code> 表示公牛，用&nbsp;<code>B</code>&nbsp;表示奶牛。</p>

<ul>
	<li><code>xA</code> 表示有 <code>x</code> 位数字出现在秘密数字中，且位置都与秘密数字一致。</li>
	<li><code>yB</code> 表示有 <code>y</code> 位数字出现在秘密数字中，但位置与秘密数字不一致。</li>
</ul>

<p>请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> secret = &quot;1807&quot;, guess = &quot;7810&quot;
<strong>输出:</strong> &quot;1A3B&quot;
<strong>解释:</strong> <code>1</code>&nbsp;公牛和&nbsp;<code>3</code>&nbsp;奶牛。公牛是 <code>8</code>，奶牛是 <code>0</code>, <code>1</code>&nbsp;和 <code>7</code>。</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> secret = &quot;1123&quot;, guess = &quot;0111&quot;
<strong>输出:</strong> &quot;1A1B&quot;
<strong>解释: </strong>朋友猜测数中的第一个 <code>1</code>&nbsp;是公牛，第二个或第三个 <code>1</code>&nbsp;可被视为奶牛。</pre>

<p>&nbsp;</p>

<p><strong>说明: </strong>你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        x = y = 0
        cnt1 = [0] * 10
        cnt2 = [0] * 10
        for i in range(len(secret)):
            if secret[i] == guess[i]:
                x += 1
            else:
                cnt1[int(secret[i])] += 1
                cnt2[int(guess[i])] += 1

        for i in range(10):
            y += min(cnt1[i], cnt2[i])
        return f'{x}A{y}B'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String getHint(String secret, String guess) {
        int x = 0, y = 0;
        int[] cnt1 = new int[10];
        int[] cnt2 = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            int a = secret.charAt(i) - '0', b = guess.charAt(i) - '0';
            if (a == b) {
                ++x;
            } else {
                ++cnt1[a];
                ++cnt2[b];
            }
        }
        for (int i = 0; i < 10; ++i) {
            y += Math.min(cnt1[i], cnt2[i]);
        }
        return String.format("%dA%dB", x, y);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string getHint(string secret, string guess) {
        int x = 0, y = 0;
        vector<int> cnt1(10);
        vector<int> cnt2(10);
        for (int i = 0; i < secret.size(); ++i)
        {
            int a = secret[i] - '0', b = guess[i] - '0';
            if (a == b) ++x;
            else
            {
                ++cnt1[a];
                ++cnt2[b];
            }
        }
        for (int i = 0; i < 10; ++i) y += min(cnt1[i], cnt2[i]);
        return to_string(x) + "A" + to_string(y) + "B";
    }
};
```

### **Go**

```go
func getHint(secret string, guess string) string {
	x, y := 0, 0
	cnt1 := make([]int, 10)
	cnt2 := make([]int, 10)
	for i := 0; i < len(secret); i++ {
		a, b := secret[i]-'0', guess[i]-'0'
		if a == b {
			x++
		} else {
			cnt1[a]++
			cnt2[b]++
		}
	}
	for i := 0; i < 10; i++ {
		y += min(cnt1[i], cnt2[i])
	}
	return fmt.Sprintf("%dA%dB", x, y)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
