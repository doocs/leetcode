# [531. 孤独像素 I](https://leetcode-cn.com/problems/lonely-pixel-i)

[English Version](/solution/0500-0599/0531.Lonely%20Pixel%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一幅黑白像素组成的图像, 计算<strong>黑色</strong>孤独像素的数量。</p>

<p>图像由一个由&lsquo;B&rsquo;和&lsquo;W&rsquo;组成二维字符数组表示, &lsquo;B&rsquo;和&lsquo;W&rsquo;分别代表黑色像素和白色像素。</p>

<p>黑色孤独像素指的是在同一行和同一列不存在其他黑色像素的黑色像素。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 
[[&#39;W&#39;, &#39;W&#39;, &#39;B&#39;],
 [&#39;W&#39;, &#39;B&#39;, &#39;W&#39;],
 [&#39;B&#39;, &#39;W&#39;, &#39;W&#39;]]

<strong>输出:</strong> 3
<strong>解析:</strong> 全部三个&#39;B&#39;都是黑色孤独像素。

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0531.Lonely%20Pixel%20I/images/pixel1.jpg" style="width: 242px; height: 242px;" />
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li>输入二维数组行和列的范围是 [1,500]。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

数组或哈希表统计每一行、每一列中 'B' 出现的次数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLonelyPixel(self, picture: List[List[str]]) -> int:
        m, n = len(picture), len(picture[0])
        rows, cols = [0] * m, [0] * n
        for i in range(m):
            for j in range(n):
                if picture[i][j] == 'B':
                    rows[i] += 1
                    cols[j] += 1
        res = 0
        for i in range(m):
            if rows[i] == 1:
                for j in range(n):
                    if picture[i][j] == 'B' and cols[j] == 1:
                        res += 1
                        break
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    ++cols[j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            if (rows[i] == 1) {
                for (int j = 0; j < n; ++j) {
                    if (picture[i][j] == 'B' && cols[j] == 1) {
                        ++res;
                        break;
                    }
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLonelyPixel(vector<vector<char>>& picture) {
        int m = picture.size(), n = picture[0].size();
        vector<int> rows(m);
        vector<int> cols(n);
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (picture[i][j] == 'B')
                {
                    ++rows[i];
                    ++cols[j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            if (rows[i] == 1)
            {
                for (int j = 0; j < n; ++j)
                {
                    if (picture[i][j] == 'B' && cols[j] == 1)
                    {
                        ++res;
                        break;
                    }
                }
            }
        }
        return res;
    }
};
```

### **Go**

```go
func findLonelyPixel(picture [][]byte) int {
	m, n := len(picture), len(picture[0])
	rows := make([]int, m)
	cols := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if picture[i][j] == 'B' {
				rows[i]++
				cols[j]++
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		if rows[i] == 1 {
			for j := 0; j < n; j++ {
				if picture[i][j] == 'B' && cols[j] == 1 {
					res++
					break
				}
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
