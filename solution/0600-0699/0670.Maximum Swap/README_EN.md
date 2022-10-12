# [670. Maximum Swap](https://leetcode.com/problems/maximum-swap)

[中文文档](/solution/0600-0699/0670.Maximum%20Swap/README.md)

## Description

<p>You are given an integer <code>num</code>. You can swap two digits at most once to get the maximum valued number.</p>

<p>Return <em>the maximum valued number you can get</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 2736
<strong>Output:</strong> 7236
<strong>Explanation:</strong> Swap the number 2 and the number 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 9973
<strong>Output:</strong> 9973
<strong>Explanation:</strong> No swap.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumSwap(self, num: int) -> int:
        s = list(str(num))
        n = len(s)
        d = list(range(n))
        for i in range(n - 2, -1, -1):
            if s[i] <= s[d[i + 1]]:
                d[i] = d[i + 1]
        for i, j in enumerate(d):
            if s[i] < s[j]:
                s[i], s[j] = s[j], s[i]
                break
        return int(''.join(s))
```

### **Java**

```java
class Solution {
    public int maximumSwap(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            d[i] = i;
        }
        for (int i = n - 2; i >= 0; --i) {
            if (s[i] <= s[d[i + 1]]) {
                d[i] = d[i + 1];
            }
        }
        for (int i = 0; i < n; ++i) {
            int j = d[i];
            if (s[i] < s[j]) {
                char t = s[i];
                s[i] = s[j];
                s[j] = t;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(s));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumSwap(int num) {
        string s = to_string(num);
        int n = s.size();
        vector<int> d(n);
        iota(d.begin(), d.end(), 0);
        for (int i = n - 2; ~i; --i) {
            if (s[i] <= s[d[i + 1]]) {
                d[i] = d[i + 1];
            }
        }
        for (int i = 0; i < n; ++i) {
            int j = d[i];
            if (s[i] < s[j]) {
                swap(s[i], s[j]);
                break;
            }
        }
        return stoi(s);
    }
};
```

### **Go**

```go
func maximumSwap(num int) int {
	s := []byte(strconv.Itoa(num))
	n := len(s)
	d := make([]int, n)
	for i := range d {
		d[i] = i
	}
	for i := n - 2; i >= 0; i-- {
		if s[i] <= s[d[i+1]] {
			d[i] = d[i+1]
		}
	}
	for i, j := range d {
		if s[i] < s[j] {
			s[i], s[j] = s[j], s[i]
			break
		}
	}
	ans, _ := strconv.Atoi(string(s))
	return ans
}
```

### **TypeScript**

```ts
function maximumSwap(num: number): number {
    const list = new Array();
    while (num !== 0) {
        list.push(num % 10);
        num = Math.floor(num / 10);
    }
    const n = list.length;
    const idx = new Array();
    for (let i = 0, j = 0; i < n; i++) {
        if (list[i] > list[j]) {
            j = i;
        }
        idx.push(j);
    }
    for (let i = n - 1; i >= 0; i--) {
        if (list[idx[i]] !== list[i]) {
            [list[idx[i]], list[i]] = [list[i], list[idx[i]]];
            break;
        }
    }
    let res = 0;
    for (let i = n - 1; i >= 0; i--) {
        res = res * 10 + list[i];
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_swap(mut num: i32) -> i32 {
        let mut list = {
            let mut res = Vec::new();
            while num != 0 {
                res.push(num % 10);
                num /= 10;
            }
            res
        };
        let n = list.len();
        let idx = {
            let mut i = 0;
            (0..n)
                .map(|j| {
                    if list[j] > list[i] {
                        i = j;
                    }
                    i
                })
                .collect::<Vec<usize>>()
        };
        for i in (0..n).rev() {
            if list[i] != list[idx[i]] {
                list.swap(i, idx[i]);
                break;
            }
        }
        let mut res = 0;
        for i in list.iter().rev() {
            res = res * 10 + i;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
