# [567. 字符串的排列](https://leetcode.cn/problems/permutation-in-string)

[English Version](/solution/0500-0599/0567.Permutation%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code> ，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的排列。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>换句话说，<code>s1</code> 的排列之一是 <code>s2</code> 的 <strong>子串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "ab" s2 = "eidbaooo"
<strong>输出：</strong>true
<strong>解释：</strong>s2 包含 s1 的排列之一 ("ba").
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1= "ab" s2 = "eidboaoo"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：滑动窗口**

我们观察发现，题目实际上等价于判断字符串 $s2$ 中是否存在窗口大小为 $n$，且窗口内的字符及其个数与字符串 $s1$ 相同的子串。

因此，我们先用哈希表或数组 $cnt1$ 统计字符串 $s1$ 中每个字符出现的次数，然后遍历字符串 $s2$，维护一个窗口大小为 $n$ 的滑动窗口，用哈希表或数组 $cnt2$ 统计窗口内每个字符出现的次数，当 $cnt1 = cnt2$ 时，说明窗口内的字符及其个数与字符串 $s1$ 相同，返回 `true` 即可。

否则，遍历结束后，返回 `false`。

时间复杂度 $(n + m \times C)$，空间复杂度 $O(C)$。其中 $n$ 和 $m$ 分别为字符串 $s1$ 和 $s2$ 的长度；而 $C$ 为字符集的大小，本题中 $C=26$。

**方法二：滑动窗口优化**

在方法一中，我们每次加入和移除一个字符时，都需要比较两个哈希表或数组，时间复杂度较高。我们可以维护一个变量 $diff$，表示两个大小为 $n$ 的字符串中，有多少种字符出现的个数不同。当 $diff=0$ 时，说明两个字符串中的字符个数相同。

时间复杂度 $O(n + m)$，空间复杂度 $O(C)$。其中 $n$ 和 $m$ 分别为字符串 $s1$ 和 $s2$ 的长度；而 $C$ 为字符集的大小，本题中 $C=26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        n = len(s1)
        cnt1 = Counter(s1)
        cnt2 = Counter(s2[:n])
        if cnt1 == cnt2:
            return True
        for i in range(n, len(s2)):
            cnt2[s2[i]] += 1
            cnt2[s2[i - n]] -= 1
            if cnt1 == cnt2:
                return True
        return False
```

```python
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        n, m = len(s1), len(s2)
        if n > m:
            return False
        cnt = Counter()
        for a, b in zip(s1, s2):
            cnt[a] -= 1
            cnt[b] += 1
        diff = sum(x != 0 for x in cnt.values())
        if diff == 0:
            return True
        for i in range(n, m):
            a, b = s2[i - n], s2[i]

            if cnt[b] == 0:
                diff += 1
            cnt[b] += 1
            if cnt[b] == 0:
                diff -= 1

            if cnt[a] == 0:
                diff += 1
            cnt[a] -= 1
            if cnt[a] == 0:
                diff -= 1

            if diff == 0:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int a = s2.charAt(i - n) - 'a';
            int b = s2.charAt(i) - 'a';
            if (cnt[b] == 0) {
                ++diff;
            }
            if (++cnt[b] == 0) {
                --diff;
            }
            if (cnt[a] == 0) {
                ++diff;
            }
            if (--cnt[a] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int n = s1.size(), m = s2.size();
        if (n > m) {
            return false;
        }
        vector<int> cnt1(26), cnt2(26);
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1[i] - 'a'];
            ++cnt2[s2[i] - 'a'];
        }
        if (cnt1 == cnt2) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2[i] - 'a'];
            --cnt2[s2[i - n] - 'a'];
            if (cnt1 == cnt2) {
                return true;
            }
        }
        return false;
    }
};
```

```cpp
class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int n = s1.size(), m = s2.size();
        if (n > m) {
            return false;
        }
        vector<int> cnt(26);
        for (int i = 0; i < n; ++i) {
            --cnt[s1[i] - 'a'];
            ++cnt[s2[i] - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int a = s2[i - n] - 'a';
            int b = s2[i] - 'a';
            if (cnt[b] == 0) {
                ++diff;
            }
            if (++cnt[b] == 0) {
                --diff;
            }
            if (cnt[a] == 0) {
                ++diff;
            }
            if (--cnt[a] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
};
```

### **Go**

```go
func checkInclusion(s1 string, s2 string) bool {
	n, m := len(s1), len(s2)
	if n > m {
		return false
	}
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for i := range s1 {
		cnt1[s1[i]-'a']++
		cnt2[s2[i]-'a']++
	}
	if cnt1 == cnt2 {
		return true
	}
	for i := n; i < m; i++ {
		cnt2[s2[i]-'a']++
		cnt2[s2[i-n]-'a']--
		if cnt1 == cnt2 {
			return true
		}
	}
	return false
}
```

```go
func checkInclusion(s1 string, s2 string) bool {
	n, m := len(s1), len(s2)
	if n > m {
		return false
	}
	cnt := [26]int{}
	for i := range s1 {
		cnt[s1[i]-'a']--
		cnt[s2[i]-'a']++
	}
	diff := 0
	for _, x := range cnt {
		if x != 0 {
			diff++
		}
	}
	if diff == 0 {
		return true
	}
	for i := n; i < m; i++ {
		a, b := s2[i-n]-'a', s2[i]-'a'
		if cnt[b] == 0 {
			diff++
		}
		cnt[b]++
		if cnt[b] == 0 {
			diff--
		}
		if cnt[a] == 0 {
			diff++
		}
		cnt[a]--
		if cnt[a] == 0 {
			diff--
		}
		if diff == 0 {
			return true
		}
	}
	return false
}
```

### **TypeScript**

```ts
function checkInclusion(s1: string, s2: string): boolean {
    // 滑动窗口方案
    if (s1.length > s2.length) {
        return false;
    }

    const n = s1.length;
    const m = s2.length;

    const toCode = (s: string) => s.charCodeAt(0) - 97;
    const isMatch = () => {
        for (let i = 0; i < 26; i++) {
            if (arr1[i] !== arr2[i]) {
                return false;
            }
        }
        return true;
    };

    const arr1 = new Array(26).fill(0);
    for (const s of s1) {
        const index = toCode(s);
        arr1[index]++;
    }

    const arr2 = new Array(26).fill(0);
    for (let i = 0; i < n; i++) {
        const index = toCode(s2[i]);
        arr2[index]++;
    }

    for (let l = 0, r = n; r < m; l++, r++) {
        if (isMatch()) {
            return true;
        }

        const i = toCode(s2[l]);
        const j = toCode(s2[r]);
        arr2[i]--;
        arr2[j]++;
    }
    return isMatch();
}
```

### **Rust**

```rust
use std::collections::HashMap;


impl Solution {
    // 测试两个哈希表是否匹配
    fn is_match(m1: &HashMap<char, i32>, m2: &HashMap<char, i32>) -> bool {
        for (k, v) in m1.iter() {
            if m2.get(k).unwrap_or(&0) != v {
                return false;
            }
        }
        true
    }
    pub fn check_inclusion(s1: String, s2: String) -> bool {
        if s1.len() > s2.len() {
            return false;
        }
        let mut m1 = HashMap::new();
        let mut m2 = HashMap::new();
        // 初始化表 1
        for c in s1.chars() {
            m1.insert(c, m1.get(&c).unwrap_or(&0) + 1);
        }
        let cs: Vec<char> = s2.chars().collect();
        // 初始化窗口
        let mut i = 0;
        while i < s1.len() {
            m2.insert(cs[i], m2.get(&cs[i]).unwrap_or(&0) + 1);
            i += 1;
        }
        if Self::is_match(&m1, &m2) {
            return true;
        }
        // 持续滑动窗口，直到匹配或超出边界
        let mut j = 0;
        while i < cs.len() {
            m2.insert(cs[j], m2.get(&cs[j]).unwrap_or(&1) - 1);
            m2.insert(cs[i], m2.get(&cs[i]).unwrap_or(&0) + 1);
            j += 1;
            i += 1;
            if Self::is_match(&m1, &m2) {
                return true;
            }
        }
        false
    }
}
```

### **Go**

```go
func checkInclusion(s1 string, s2 string) bool {
	need, window := make(map[byte]int), make(map[byte]int)
	validate, left, right := 0, 0, 0
	for i := range s1 {
		need[s1[i]] += 1
	}
	for ; right < len(s2); right++ {
		c := s2[right]
		window[c] += 1
		if need[c] == window[c] {
			validate++
		}
		for right-left+1 >= len(s1) {
			if validate == len(need) {
				return true
			}
			d := s2[left]
			if need[d] == window[d] {
				validate--
			}
			window[d] -= 1
			left++
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
