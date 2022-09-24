# [1652. 拆炸弹](https://leetcode.cn/problems/defuse-the-bomb)

[English Version](/solution/1600-1699/1652.Defuse%20the%20Bomb/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 <code>n</code> 的 <strong>循环</strong> 数组 <code>code</code> 以及一个密钥 <code>k</code> 。</p>

<p>为了获得正确的密码，你需要替换掉每一个数字。所有数字会 <strong>同时</strong> 被替换。</p>

<ul>
	<li>如果 <code>k > 0</code> ，将第 <code>i</code> 个数字用 <strong>接下来</strong> <code>k</code> 个数字之和替换。</li>
	<li>如果 <code>k < 0</code> ，将第 <code>i</code> 个数字用 <strong>之前</strong> <code>k</code> 个数字之和替换。</li>
	<li>如果 <code>k == 0</code> ，将第 <code>i</code> 个数字用 <code>0</code> 替换。</li>
</ul>

<p>由于 <code>code</code> 是循环的， <code>code[n-1]</code> 下一个元素是 <code>code[0]</code> ，且 <code>code[0]</code> 前一个元素是 <code>code[n-1]</code> 。</p>

<p>给你 <strong>循环</strong> 数组 <code>code</code> 和整数密钥 <code>k</code> ，请你返回解密后的结果来拆除炸弹！</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>code = [5,7,1,4], k = 3
<b>输出：</b>[12,10,16,13]
<b>解释：</b>每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>code = [1,2,3,4], k = 0
<b>输出：</b>[0,0,0,0]
<b>解释：</b>当 k 为 0 时，所有数字都被 0 替换。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>code = [2,4,9,3], k = -2
<b>输出：</b>[12,5,6,13]
<b>解释：</b>解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 <strong>之前</strong> 的数字。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == code.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>1 <= code[i] <= 100</code></li>
	<li><code>-(n - 1) <= k <= n - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

定义答案数组 `ans`，长度为 $n$，初始时所有元素都为 $0$。根据题意，若 $k$ 为 $0$，直接返回 `ans`。

否则，遍历每个位置 $i$：

若 $k$ 为正数，那么 $i$ 位置的值为 $i$ 位置后 $k$ 个位置的值之和，即：

$$
ans[i] = \sum_{j=i+1}^{i+k} code[j\mod{n}]
$$

若 $k$ 为负数，那么 $i$ 位置的值为 $i$ 位置前 $|k|$ 个位置的值之和，即：

$$
ans[i] = \sum_{j=i+k}^{i-1} code[(j+n)\mod{n}]
$$

时间复杂度 $O(n\times|k|)$，忽略答案的空间消耗，空间复杂度 $O(1)$。

**方法二：前缀和**

在方法一中，对于每个位置 $i$，都需要遍历 $k$ 个位置，有很多重复计算的操作。我们可以利用前缀和来优化。

我们将 `code` 数组复制一份（可以不用执行复制操作，直接通过循环遍历取模实现），得到两倍长度的数组，对其求前缀和，得到长度为 $2\times n + 1$ 的前缀和数组 $s$。

若 $k$ 为正数，那么 $i$ 位置的值为 $i$ 位置后 $k$ 个位置的值之和，即 $ans[i] = s[i + k + 1] - s[i + 1]$。

若 $k$ 为负数，那么 $i$ 位置的值为 $i$ 位置前 $|k|$ 个位置的值之和，即 $ans[i] = s[i + n] - s[i + k + n]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `code` 数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def decrypt(self, code: List[int], k: int) -> List[int]:
        n = len(code)
        ans = [0] * n
        if k == 0:
            return ans
        for i in range(n):
            if k > 0:
                for j in range(i + 1, i + k + 1):
                    ans[i] += code[j % n]
            else:
                for j in range(i + k, i):
                    ans[i] += code[(j + n) % n]
        return ans
```

```python
class Solution:
    def decrypt(self, code: List[int], k: int) -> List[int]:
        n = len(code)
        ans = [0] * n
        if k == 0:
            return ans
        s = list(accumulate(code + code, initial=0))
        for i in range(n):
            if k > 0:
                ans[i] = s[i + k + 1] - s[i + 1]
            else:
                ans[i] = s[i + n] - s[i + k + n]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                for (int j = i + 1; j < i + k + 1; ++j) {
                    ans[i] += code[j % n];
                }
            } else {
                for (int j = i + k; j < i; ++j) {
                    ans[i] += code[(j + n) % n];
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        int[] s = new int[n << 1 | 1];
        for (int i = 0; i < n << 1; ++i) {
            s[i + 1] = s[i] + code[i % n];
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                ans[i] = s[i + k + 1] - s[i + 1];
            } else {
                ans[i] = s[i + n] - s[i + k + n];
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
    vector<int> decrypt(vector<int>& code, int k) {
        int n = code.size();
        vector<int> ans(n);
        if (k == 0) {
            return ans;
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                for (int j = i + 1; j < i + k + 1; ++j) {
                    ans[i] += code[j % n];
                }
            } else {
                for (int j = i + k; j < i; ++j) {
                    ans[i] += code[(j + n) % n];
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> decrypt(vector<int>& code, int k) {
        int n = code.size();
        vector<int> ans(n);
        if (k == 0) {
            return ans;
        }
        vector<int> s(n << 1 | 1);
        for (int i = 0; i < n << 1; ++i) {
            s[i + 1] = s[i] + code[i % n];
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                ans[i] = s[i + k + 1] - s[i + 1];
            } else {
                ans[i] = s[i + n] - s[i + k + n];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func decrypt(code []int, k int) []int {
	n := len(code)
	ans := make([]int, n)
	if k == 0 {
		return ans
	}
	for i := 0; i < n; i++ {
		if k > 0 {
			for j := i + 1; j < i+k+1; j++ {
				ans[i] += code[j%n]
			}
		} else {
			for j := i + k; j < i; j++ {
				ans[i] += code[(j+n)%n]
			}
		}
	}
	return ans
}
```

```go
func decrypt(code []int, k int) []int {
	n := len(code)
	ans := make([]int, n)
	if k == 0 {
		return ans
	}
	s := make([]int, n<<1|1)
	for i := 0; i < n<<1; i++ {
		s[i+1] = s[i] + code[i%n]
	}
	for i := range code {
		if k > 0 {
			ans[i] = s[i+k+1] - s[i+1]
		} else {
			ans[i] = s[i+n] - s[i+k+n]
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function decrypt(code: number[], k: number): number[] {
    const n = code.length;
    if (k === 0) {
        return code.fill(0);
    }
    const isPrefix = k < 0;
    if (isPrefix) {
        k *= -1;
    }
    const map = new Map<number, [number, number]>();
    let prefix = 0;
    let suffix = 0;
    for (let i = 1; i <= k; i++) {
        prefix += code[n - i];
        suffix += code[i];
    }
    map.set(0, [prefix, suffix]);

    for (let i = 1; i < n; i++) {
        let [p, s] = map.get(i - 1);
        p -= code[n - k - 1 + i] ?? code[i - k - 1];
        p += code[i - 1];
        s -= code[i];
        s += code[i + k] ?? code[i + k - n];
        map.set(i, [p, s]);
    }
    for (let i = 0; i < n; i++) {
        code[i] = map.get(i)[Number(!isPrefix)];
    }
    return code;
}
```

### **...**

```

```

<!-- tabs:end -->
