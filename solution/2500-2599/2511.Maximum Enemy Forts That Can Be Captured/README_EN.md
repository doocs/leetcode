# [2511. Maximum Enemy Forts That Can Be Captured](https://leetcode.com/problems/maximum-enemy-forts-that-can-be-captured)

[中文文档](/solution/2500-2599/2511.Maximum%20Enemy%20Forts%20That%20Can%20Be%20Captured/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>forts</code> of length <code>n</code> representing the positions of several forts. <code>forts[i]</code> can be <code>-1</code>, <code>0</code>, or <code>1</code> where:</p>

<ul>
	<li><code>-1</code> represents there is <strong>no fort</strong> at the <code>i<sup>th</sup></code> position.</li>
	<li><code>0</code> indicates there is an <strong>enemy</strong> fort at the <code>i<sup>th</sup></code> position.</li>
	<li><code>1</code> indicates the fort at the <code>i<sup>th</sup></code> the position is under your command.</li>
</ul>

<p>Now you have decided to move your army from one of your forts at position <code>i</code> to an empty position <code>j</code> such that:</p>

<ul>
	<li><code>0 &lt;= i, j &lt;= n - 1</code></li>
	<li>The army travels over enemy forts <strong>only</strong>. Formally, for all <code>k</code> where <code>min(i,j) &lt; k &lt; max(i,j)</code>, <code>forts[k] == 0.</code></li>
</ul>

<p>While moving the army, all the enemy forts that come in the way are <strong>captured</strong>.</p>

<p>Return<em> the <strong>maximum</strong> number of enemy forts that can be captured</em>. In case it is <strong>impossible</strong> to move your army, or you do not have any fort under your command, return <code>0</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> forts = [1,0,0,-1,0,0,0,0,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
- Moving the army from position 0 to position 3 captures 2 enemy forts, at 1 and 2.
- Moving the army from position 8 to position 3 captures 4 enemy forts.
Since 4 is the maximum number of enemy forts that can be captured, we return 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> forts = [0,0,1,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since no enemy fort can be captured, 0 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= forts.length &lt;= 1000</code></li>
	<li><code>-1 &lt;= forts[i] &lt;= 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def captureForts(self, forts: List[int]) -> int:
        n = len(forts)
        i = ans = 0
        while i < n:
            j = i + 1
            if forts[i]:
                while j < n and forts[j] == 0:
                    j += 1
                if j < n and forts[i] + forts[j] == 0:
                    ans = max(ans, j - i - 1)
            i = j
        return ans
```

### **Java**

```java
class Solution {
    public int captureForts(int[] forts) {
        int n = forts.length;
        int ans = 0, i = 0;
        while (i < n) {
            int j = i + 1;
            if (forts[i] != 0) {
                while (j < n && forts[j] == 0) {
                    ++j;
                }
                if (j < n && forts[i] + forts[j] == 0) {
                    ans = Math.max(ans, j - i - 1);
                }
            }
            i = j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int captureForts(vector<int>& forts) {
        int n = forts.size();
        int ans = 0, i = 0;
        while (i < n) {
            int j = i + 1;
            if (forts[i] != 0) {
                while (j < n && forts[j] == 0) {
                    ++j;
                }
                if (j < n && forts[i] + forts[j] == 0) {
                    ans = max(ans, j - i - 1);
                }
            }
            i = j;
        }
        return ans;
    }
};
```

### **Go**

```go
func captureForts(forts []int) (ans int) {
	n := len(forts)
	i := 0
	for i < n {
		j := i + 1
		if forts[i] != 0 {
			for j < n && forts[j] == 0 {
				j++
			}
			if j < n && forts[i]+forts[j] == 0 {
				ans = max(ans, j-i-1)
			}
		}
		i = j
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function captureForts(forts: number[]): number {
    const n = forts.length;
    let ans = 0;
    let i = 0;
    while (i < n) {
        let j = i + 1;
        if (forts[i] !== 0) {
            while (j < n && forts[j] === 0) {
                j++;
            }
            if (j < n && forts[i] + forts[j] === 0) {
                ans = Math.max(ans, j - i - 1);
            }
        }
        i = j;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn capture_forts(forts: Vec<i32>) -> i32 {
        let n = forts.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            if forts[i] != 0 {
                while j < n && forts[j] == 0 {
                    j += 1;
                }
                if j < n && forts[i] + forts[j] == 0 {
                    ans = ans.max(j - i - 1);
                }
            }
            i = j;
        }
        ans as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
