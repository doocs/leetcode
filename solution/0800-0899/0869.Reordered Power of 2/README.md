# [869. 重新排序得到 2 的幂](https://leetcode.cn/problems/reordered-power-of-2)

[English Version](/solution/0800-0899/0869.Reordered%20Power%20of%202/README_EN.md)

<!-- tags:哈希表,数学,计数,枚举,排序 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定正整数&nbsp;<code>n</code>&nbsp;，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。</p>

<p>如果我们可以通过上述方式得到&nbsp;2 的幂，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def reorderedPowerOf2(self, n: int) -> bool:
        def convert(n):
            cnt = [0] * 10
            while n:
                n, v = divmod(n, 10)
                cnt[v] += 1
            return cnt

        i, s = 1, convert(n)
        while i <= 10**9:
            if convert(i) == s:
                return True
            i <<= 1
        return False
```

```java
class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = convert(n);
        for (int i = 1; i <= Math.pow(10, 9); i <<= 1) {
            if (s.equals(convert(i))) {
                return true;
            }
        }
        return false;
    }

    private String convert(int n) {
        char[] cnt = new char[10];
        for (; n > 0; n /= 10) {
            cnt[n % 10]++;
        }
        return new String(cnt);
    }
}
```

```cpp
class Solution {
public:
    bool reorderedPowerOf2(int n) {
        vector<int> s = convert(n);
        for (int i = 1; i <= pow(10, 9); i <<= 1)
            if (s == convert(i))
                return true;
        return false;
    }

    vector<int> convert(int n) {
        vector<int> cnt(10);
        for (; n; n /= 10) ++cnt[n % 10];
        return cnt;
    }
};
```

```go
func reorderedPowerOf2(n int) bool {
	convert := func(n int) []byte {
		cnt := make([]byte, 10)
		for ; n > 0; n /= 10 {
			cnt[n%10]++
		}
		return cnt
	}
	s := convert(n)
	for i := 1; i <= 1e9; i <<= 1 {
		if bytes.Equal(s, convert(i)) {
			return true
		}
	}
	return false
}
```

<!-- tabs:end -->

<!-- end -->
