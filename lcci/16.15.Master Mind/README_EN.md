# [16.15. Master Mind](https://leetcode.cn/problems/master-mind-lcci)

[中文文档](/lcci/16.15.Master%20Mind/README.md)

## Description

<p>The Game of Master Mind is played as follows:</p>
<p>The computer has four slots, and each slot will contain a ball that is red (R). yellow (Y). green (G) or blue (B). For example, the computer might have RGGB (Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue).</p>
<p>You, the user, are trying to guess the solution. You might, for example, guess YRGB.</p>
<p>When you guess the correct color for the correct slot, you get a &quot;hit:&#39; If you guess a color that exists but is in the wrong slot, you get a &quot;pseudo-hit:&#39; Note that a slot that is a hit can never count as a pseudo-hit.</p>
<p>For example, if the actual solution is RGBY and you guess GGRR, you have one hit and one pseudo-hit. Write a method that, given a guess and a solution, returns the number of hits and pseudo-hits.</p>
<p>Given a sequence of colors <code>solution</code>, and a <code>guess</code>, write a method that return the number of hits and pseudo-hit <code>answer</code>, where <code>answer[0]</code> is the number of hits and <code>answer[1]</code> is the number of pseudo-hit.</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong> solution=&quot;RGBY&quot;,guess=&quot;GGRR&quot;

<strong>Output: </strong> [1,1]

<strong>Explanation: </strong> hit once, pseudo-hit once.

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>len(solution) = len(guess) = 4</code></li>
	<li>There are only <code>&quot;R&quot;</code>,<code>&quot;G&quot;</code>,<code>&quot;B&quot;</code>,<code>&quot;Y&quot;</code> in <code>solution</code>&nbsp;and&nbsp;<code>guess</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def masterMind(self, solution: str, guess: str) -> List[int]:
        x = sum(a == b for a, b in zip(solution, guess))
        y = sum((Counter(solution) & Counter(guess)).values())
        return [x, y - x]
```

### **Java**

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
