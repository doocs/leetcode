# [949. Largest Time for Given Digits](https://leetcode.com/problems/largest-time-for-given-digits)

[中文文档](/solution/0900-0999/0949.Largest%20Time%20for%20Given%20Digits/README.md)

## Description

<p>Given an array <code>arr</code> of 4 digits, find the latest 24-hour time that can be made using each digit <strong>exactly once</strong>.</p>

<p>24-hour times are formatted as <code>&quot;HH:MM&quot;</code>, where <code>HH</code> is between <code>00</code> and <code>23</code>, and <code>MM</code> is between <code>00</code> and <code>59</code>. The earliest 24-hour time is <code>00:00</code>, and the latest is <code>23:59</code>.</p>

<p>Return <em>the latest 24-hour time in <code>&quot;HH:MM&quot;</code> format</em>. If no valid time can be made, return an empty string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4]
<strong>Output:</strong> &quot;23:41&quot;
<strong>Explanation:</strong> The valid 24-hour times are &quot;12:34&quot;, &quot;12:43&quot;, &quot;13:24&quot;, &quot;13:42&quot;, &quot;14:23&quot;, &quot;14:32&quot;, &quot;21:34&quot;, &quot;21:43&quot;, &quot;23:14&quot;, and &quot;23:41&quot;. Of these times, &quot;23:41&quot; is the latest.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [5,5,5,5]
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are no valid 24-hour times as &quot;55:55&quot; is not valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>arr.length == 4</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestTimeFromDigits(self, arr: List[int]) -> str:
        cnt = [0] * 10
        for v in arr:
            cnt[v] += 1
        for h in range(23, -1, -1):
            for m in range(59, -1, -1):
                t = [0] * 10
                t[h // 10] += 1
                t[h % 10] += 1
                t[m // 10] += 1
                t[m % 10] += 1
                if cnt == t:
                    return f'{h:02}:{m:02}'
        return ''
```

```python
class Solution:
    def largestTimeFromDigits(self, arr: List[int]) -> str:
        ans = -1
        for i in range(4):
            for j in range(4):
                for k in range(4):
                    if i != j and i != k and j != k:
                        h = arr[i] * 10 + arr[j]
                        m = arr[k] * 10 + arr[6 - i - j - k]
                        if h < 24 and m < 60:
                            ans = max(ans, h * 60 + m)
        return '' if ans < 0 else f'{ans // 60:02}:{ans % 60:02}'
```

### **Java**

```java
class Solution {
    public String largestTimeFromDigits(int[] arr) {
        int ans = -1;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i != j && j != k && i != k) {
                        int h = arr[i] * 10 + arr[j];
                        int m = arr[k] * 10 + arr[6 - i - j - k];
                        if (h < 24 && m < 60) {
                            ans = Math.max(ans, h * 60 + m);
                        }
                    }
                }
            }
        }
        return ans < 0 ? "" : String.format("%02d:%02d", ans / 60, ans % 60);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string largestTimeFromDigits(vector<int>& arr) {
        int ans = -1;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i != j && j != k && i != k) {
                        int h = arr[i] * 10 + arr[j];
                        int m = arr[k] * 10 + arr[6 - i - j - k];
                        if (h < 24 && m < 60) {
                            ans = max(ans, h * 60 + m);
                        }
                    }
                }
            }
        }
        if (ans < 0) return "";
        int h = ans / 60, m = ans % 60;
        return to_string(h / 10) + to_string(h % 10) + ":" + to_string(m / 10) + to_string(m % 10);
    }
};
```

### **Go**

```go
func largestTimeFromDigits(arr []int) string {
	ans := -1
	for i := 0; i < 4; i++ {
		for j := 0; j < 4; j++ {
			for k := 0; k < 4; k++ {
				if i != j && j != k && i != k {
					h := arr[i]*10 + arr[j]
					m := arr[k]*10 + arr[6-i-j-k]
					if h < 24 && m < 60 {
						ans = max(ans, h*60+m)
					}
				}
			}
		}
	}
	if ans < 0 {
		return ""
	}
	return fmt.Sprintf("%02d:%02d", ans/60, ans%60)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
