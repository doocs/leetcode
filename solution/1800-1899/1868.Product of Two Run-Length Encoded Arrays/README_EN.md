# [1868. Product of Two Run-Length Encoded Arrays](https://leetcode.com/problems/product-of-two-run-length-encoded-arrays)

[中文文档](/solution/1800-1899/1868.Product%20of%20Two%20Run-Length%20Encoded%20Arrays/README.md)

## Description

<p><strong>Run-length encoding</strong> is a compression algorithm that allows for an integer array <code>nums</code> with many segments of <strong>consecutive repeated</strong> numbers to be represented by a (generally smaller) 2D array <code>encoded</code>. Each <code>encoded[i] = [val<sub>i</sub>, freq<sub>i</sub>]</code> describes the <code>i<sup>th</sup></code> segment of repeated numbers in <code>nums</code> where <code>val<sub>i</sub></code> is the value that is repeated <code>freq<sub>i</sub></code> times.</p>

<ul>
	<li>For example, <code>nums = [1,1,1,2,2,2,2,2]</code> is represented by the <strong>run-length encoded</strong> array <code>encoded = [[1,3],[2,5]]</code>. Another way to read this is &quot;three <code>1</code>&#39;s followed by five <code>2</code>&#39;s&quot;.</li>
</ul>

<p>The <strong>product</strong> of two run-length encoded arrays <code>encoded1</code> and <code>encoded2</code> can be calculated using the following steps:</p>

<ol>
	<li><strong>Expand</strong> both <code>encoded1</code> and <code>encoded2</code> into the full arrays <code>nums1</code> and <code>nums2</code> respectively.</li>
	<li>Create a new array <code>prodNums</code> of length <code>nums1.length</code> and set <code>prodNums[i] = nums1[i] * nums2[i]</code>.</li>
	<li><strong>Compress</strong> <code>prodNums</code> into a run-length encoded array and return it.</li>
</ol>

<p>You are given two <strong>run-length encoded</strong> arrays <code>encoded1</code> and <code>encoded2</code> representing full arrays <code>nums1</code> and <code>nums2</code> respectively. Both <code>nums1</code> and <code>nums2</code> have the <strong>same length</strong>. Each <code>encoded1[i] = [val<sub>i</sub>, freq<sub>i</sub>]</code> describes the <code>i<sup>th</sup></code> segment of <code>nums1</code>, and each <code>encoded2[j] = [val<sub>j</sub>, freq<sub>j</sub>]</code> describes the <code>j<sup>th</sup></code> segment of <code>nums2</code>.</p>

<p>Return <i>the <strong>product</strong> of </i><code>encoded1</code><em> and </em><code>encoded2</code>.</p>

<p><strong>Note:</strong> Compression should be done such that the run-length encoded array has the <strong>minimum</strong> possible length.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
<strong>Output:</strong> [[6,6]]
<strong>Explanation:</strong> encoded1 expands to [1,1,1,2,2,2] and encoded2 expands to [6,6,6,3,3,3].
prodNums = [6,6,6,6,6,6], which is compressed into the run-length encoded array [[6,6]].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
<strong>Output:</strong> [[2,3],[6,1],[9,2]]
<strong>Explanation:</strong> encoded1 expands to [1,1,1,2,3,3] and encoded2 expands to [2,2,2,3,3,3].
prodNums = [2,2,2,6,9,9], which is compressed into the run-length encoded array [[2,3],[6,1],[9,2]].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= encoded1.length, encoded2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>encoded1[i].length == 2</code></li>
	<li><code>encoded2[j].length == 2</code></li>
	<li><code>1 &lt;= val<sub>i</sub>, freq<sub>i</sub> &lt;= 10<sup>4</sup></code> for each <code>encoded1[i]</code>.</li>
	<li><code>1 &lt;= val<sub>j</sub>, freq<sub>j</sub> &lt;= 10<sup>4</sup></code> for each <code>encoded2[j]</code>.</li>
	<li>The full arrays that <code>encoded1</code> and <code>encoded2</code> represent are the same length.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRLEArray(self, encoded1: List[List[int]], encoded2: List[List[int]]) -> List[List[int]]:
        ans = []
        j = 0
        for vi, fi in encoded1:
            while fi:
                f = min(fi, encoded2[j][1])
                v = vi * encoded2[j][0]
                if ans and ans[-1][0] == v:
                    ans[-1][1] += f
                else:
                    ans.append([v, f])
                fi -= f
                encoded2[j][1] -= f
                if encoded2[j][1] == 0:
                    j += 1
        return ans
```

### **Java**

```java
class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> ans = new ArrayList<>();
        int j = 0;
        for (var e : encoded1) {
            int vi = e[0], fi = e[1];
            while (fi > 0) {
                int f = Math.min(fi, encoded2[j][1]);
                int v = vi * encoded2[j][0];
                int m = ans.size();
                if (m > 0 && ans.get(m - 1).get(0) == v) {
                    ans.get(m - 1).set(1, ans.get(m - 1).get(1) + f);
                } else {
                    ans.add(new ArrayList<>(List.of(v, f)));
                }
                fi -= f;
                encoded2[j][1] -= f;
                if (encoded2[j][1] == 0) {
                    ++j;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> findRLEArray(vector<vector<int>>& encoded1, vector<vector<int>>& encoded2) {
        vector<vector<int>> ans;
        int j = 0;
        for (auto& e : encoded1) {
            int vi = e[0], fi = e[1];
            while (fi) {
                int f = min(fi, encoded2[j][1]);
                int v = vi * encoded2[j][0];
                if (!ans.empty() && ans.back()[0] == v) {
                    ans.back()[1] += f;
                } else {
                    ans.push_back({v, f});
                }
                fi -= f;
                encoded2[j][1] -= f;
                if (encoded2[j][1] == 0) {
                    ++j;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findRLEArray(encoded1 [][]int, encoded2 [][]int) (ans [][]int) {
	j := 0
	for _, e := range encoded1 {
		vi, fi := e[0], e[1]
		for fi > 0 {
			f := min(fi, encoded2[j][1])
			v := vi * encoded2[j][0]
			if len(ans) > 0 && ans[len(ans)-1][0] == v {
				ans[len(ans)-1][1] += f
			} else {
				ans = append(ans, []int{v, f})
			}
			fi -= f
			encoded2[j][1] -= f
			if encoded2[j][1] == 0 {
				j++
			}
		}
	}
	return
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
