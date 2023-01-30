# [面试题 16.15. 珠玑妙算](https://leetcode.cn/problems/master-mind-lcci)

[English Version](/lcci/16.15.Master%20Mind/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>珠玑妙算游戏（the game of master mind）的玩法如下。</p>
<p>计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。</p>
<p>给定一种颜色组合<code>solution</code>和一个猜测<code>guess</code>，编写一个方法，返回猜中和伪猜中的次数<code>answer</code>，其中<code>answer[0]</code>为猜中的次数，<code>answer[1]</code>为伪猜中的次数。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> solution="RGBY",guess="GGRR"
<strong>输出：</strong> [1,1]
<strong>解释：</strong> 猜中1次，伪猜中1次。
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>len(solution) = len(guess) = 4</code></li>
<li><code>solution</code>和<code>guess</code>仅包含<code>"R"</code>,<code>"G"</code>,<code>"B"</code>,<code>"Y"</code>这4种字符</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

同时遍历两个字符串，算出对应位置字符相同的个数，累加到 $x$ 中，然后将两个字符串出现的字符以及出现的次数分别记录在哈希表 `cnt1` 和 `cnt2` 中。

接着遍历两个哈希表，算出有多少共同出现的字符，累加到 $y$ 中。那么答案就是 $[x, y - x]$。

时间复杂度 $O(C)$，空间复杂度 $O(C)$。本题中 $C=4$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def masterMind(self, solution: str, guess: str) -> List[int]:
        x = sum(a == b for a, b in zip(solution, guess))
        y = sum((Counter(solution) & Counter(guess)).values())
        return [x, y - x]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] masterMind(String solution, String guess) {
        int x = 0, y = 0;
        Map<Character, Integer> cnt1 = new HashMap<>();
        Map<Character, Integer> cnt2 = new HashMap<>();
        for (int i = 0; i < 4; ++i) {
            char a = solution.charAt(i), b = guess.charAt(i);
            x += a == b ? 1 : 0;
            cnt1.merge(a, 1, Integer::sum);
            cnt2.merge(b, 1, Integer::sum);
        }
        for (char c : "RYGB".toCharArray()) {
            y += Math.min(cnt1.getOrDefault(c, 0), cnt2.getOrDefault(c, 0));
        }
        return new int[] {x, y - x};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> masterMind(string solution, string guess) {
        int x = 0, y = 0;
        unordered_map<char, int> cnt1;
        unordered_map<char, int> cnt2;
        for (int i = 0; i < 4; ++i) {
            x += solution[i] == guess[i];
            cnt1[solution[i]]++;
            cnt2[guess[i]]++;
        }
        for (char c : "RYGB") y += min(cnt1[c], cnt2[c]);
        return vector<int>{x, y - x};
    }
};
```

### **Go**

```go
func masterMind(solution string, guess string) []int {
	var x, y int
	cnt1 := map[byte]int{}
	cnt2 := map[byte]int{}
	for i := range solution {
		a, b := solution[i], guess[i]
		if a == b {
			x++
		}
		cnt1[a]++
		cnt2[b]++
	}
	for _, c := range []byte("RYGB") {
		y += min(cnt1[c], cnt2[c])
	}
	return []int{x, y - x}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {string} solution
 * @param {string} guess
 * @return {number[]}
 */
var masterMind = function (solution, guess) {
    let counts1 = { R: 0, G: 0, B: 0, Y: 0 };
    let counts2 = { R: 0, G: 0, B: 0, Y: 0 };
    let res1 = 0;
    for (let i = 0; i < solution.length; i++) {
        let s1 = solution.charAt(i),
            s2 = guess.charAt(i);
        if (s1 == s2) {
            res1++;
        } else {
            counts1[s1] += 1;
            counts2[s2] += 1;
        }
    }
    let res2 = ['R', 'G', 'B', 'Y'].reduce(
        (a, c) => a + Math.min(counts1[c], counts2[c]),
        0,
    );
    return [res1, res2];
};
```

### **...**

```

```

<!-- tabs:end -->
