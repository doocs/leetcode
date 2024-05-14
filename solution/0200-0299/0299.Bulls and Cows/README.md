# [299. 猜数字游戏](https://leetcode.cn/problems/bulls-and-cows)

[English Version](/solution/0200-0299/0299.Bulls%20and%20Cows/README_EN.md)

<!-- tags:哈希表,字符串,计数 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>你在和朋友一起玩 <a href="https://baike.baidu.com/item/%E7%8C%9C%E6%95%B0%E5%AD%97/83200?fromtitle=Bulls+and+Cows&amp;fromid=12003488&amp;fr=aladdin" target="_blank">猜数字（Bulls and Cows）</a>游戏，该游戏规则如下：</p>

<p>写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：</p>

<ul>
	<li>猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls"，公牛），</li>
	<li>有多少位属于数字猜对了但是位置不对（称为 "Cows"，奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。</li>
</ul>

<p>给你一个秘密数字&nbsp;<code>secret</code> 和朋友猜测的数字&nbsp;<code>guess</code> ，请你返回对朋友这次猜测的提示。</p>

<p>提示的格式为 <code>"xAyB"</code> ，<code>x</code> 是公牛个数， <code>y</code> 是奶牛个数，<code>A</code> 表示公牛，<code>B</code>&nbsp;表示奶牛。</p>

<p>请注意秘密数字和朋友猜测的数字都可能含有重复数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>secret = "1807", guess = "7810"
<strong>输出：</strong>"1A3B"
<strong>解释：</strong>数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
"1807"
  |
"<em><strong>7</strong></em>8<em><strong>10</strong></em>"</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>secret = "1123", guess = "0111"
<strong>输出：</strong>"1A1B"
<strong>解释：</strong>数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
"1123"        "1123"
  |      or     |
"01<em><strong>1</strong></em>1"        "011<em><strong>1</strong></em>"
注意，两个不匹配的 1 中，只有一个会算作奶牛（数字猜对位置不对）。通过重新排列非公牛数字，其中仅有一个 1 可以成为公牛数字。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= secret.length, guess.length &lt;= 1000</code></li>
	<li><code>secret.length == guess.length</code></li>
	<li><code>secret</code> 和 <code>guess</code> 仅由数字组成</li>
</ul>

## 解法

### 方法一：计数

我们创建两个计数器 $cnt1$ 和 $cnt2$，分别用来统计秘密数字和朋友猜测的数字中每个数字出现的次数。同时，我们创建一个变量 $x$ 来统计公牛的数量。

然后我们遍历秘密数字和朋友猜测的数字，如果当前数字相同，我们就将 $x$ 加一。否则，我们分别将秘密数字和朋友猜测的数字中的当前数字的计数加一。

最后，我们遍历 $cnt1$ 中的每个数字，取 $cnt1$ 和 $cnt2$ 中当前数字的计数的最小值，然后将这个最小值加到变量 $y$ 中。

最后，我们返回 $x$ 和 $y$ 的值。

时间复杂度 $O(n)$，其中 $n$ 是秘密数字和朋友猜测的数字的长度。空间复杂度 $O(|\Sigma|)$，其中 $|\Sigma|$ 是字符集的大小，本题中字符集为数字，所以 $|\Sigma| = 10$。

<!-- tabs:start -->

```python
class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        cnt1, cnt2 = Counter(), Counter()
        x = 0
        for a, b in zip(secret, guess):
            if a == b:
                x += 1
            else:
                cnt1[a] += 1
                cnt2[b] += 1
        y = sum(min(cnt1[c], cnt2[c]) for c in cnt1)
        return f"{x}A{y}B"
```

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

```cpp
class Solution {
public:
    string getHint(string secret, string guess) {
        int x = 0, y = 0;
        int cnt1[10]{};
        int cnt2[10]{};
        for (int i = 0; i < secret.size(); ++i) {
            int a = secret[i] - '0', b = guess[i] - '0';
            if (a == b) {
                ++x;
            } else {
                ++cnt1[a];
                ++cnt2[b];
            }
        }
        for (int i = 0; i < 10; ++i) {
            y += min(cnt1[i], cnt2[i]);
        }
        return to_string(x) + "A" + to_string(y) + "B";
    }
};
```

```go
func getHint(secret string, guess string) string {
	x, y := 0, 0
	cnt1 := [10]int{}
	cnt2 := [10]int{}
	for i, c := range secret {
		a, b := int(c-'0'), int(guess[i]-'0')
		if a == b {
			x++
		} else {
			cnt1[a]++
			cnt2[b]++
		}
	}
	for i, c := range cnt1 {
		y += min(c, cnt2[i])

	}
	return fmt.Sprintf("%dA%dB", x, y)
}
```

```ts
function getHint(secret: string, guess: string): string {
    const cnt1: number[] = Array(10).fill(0);
    const cnt2: number[] = Array(10).fill(0);
    let x: number = 0;
    for (let i = 0; i < secret.length; ++i) {
        if (secret[i] === guess[i]) {
            ++x;
        } else {
            ++cnt1[+secret[i]];
            ++cnt2[+guess[i]];
        }
    }
    let y: number = 0;
    for (let i = 0; i < 10; ++i) {
        y += Math.min(cnt1[i], cnt2[i]);
    }
    return `${x}A${y}B`;
}
```

```php
class Solution {
    /**
     * @param String $secret
     * @param String $guess
     * @return String
     */
    function getHint($secret, $guess) {
        $cnt1 = array_fill(0, 10, 0);
        $cnt2 = array_fill(0, 10, 0);
        $x = 0;
        for ($i = 0; $i < strlen($secret); ++$i) {
            if ($secret[$i] === $guess[$i]) {
                ++$x;
            } else {
                ++$cnt1[(int) $secret[$i]];
                ++$cnt2[(int) $guess[$i]];
            }
        }
        $y = 0;
        for ($i = 0; $i < 10; ++$i) {
            $y += min($cnt1[$i], $cnt2[$i]);
        }
        return "{$x}A{$y}B";
    }
}
```

<!-- tabs:end -->

<!-- end -->
