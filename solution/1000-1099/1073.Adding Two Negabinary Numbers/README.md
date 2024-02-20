# [1073. 负二进制数相加](https://leetcode.cn/problems/adding-two-negabinary-numbers)

[English Version](/solution/1000-1099/1073.Adding%20Two%20Negabinary%20Numbers/README_EN.md)

<!-- tags:数组,数学 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给出基数为 <strong>-2</strong>&nbsp;的两个数&nbsp;<code>arr1</code> 和&nbsp;<code>arr2</code>，返回两数相加的结果。</p>

<p>数字以&nbsp;<em>数组形式</em><strong>&nbsp;</strong>给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，<code>arr&nbsp;= [1,1,0,1]</code>&nbsp;表示数字&nbsp;<code>(-2)^3&nbsp;+ (-2)^2 + (-2)^0 = -3</code>。<em>数组形式</em>&nbsp;中的数字 <code>arr</code> 也同样不含前导零：即&nbsp;<code>arr == [0]</code>&nbsp;或&nbsp;<code>arr[0] == 1</code>。</p>

<p>返回相同表示形式的 <code>arr1</code> 和 <code>arr2</code> 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr1 = [1,1,1,1,1], arr2 = [1,0,1]
<strong>输出：</strong>[1,0,0,0,0]
<strong>解释：</strong>arr1 表示 11，arr2 表示 5，输出表示 16 。
</pre>

<p><meta charset="UTF-8" /></p>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr1 = [0], arr2 = [0]
<strong>输出：</strong>[0]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr1 = [0], arr2 = [1]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= arr1.length,&nbsp;arr2.length &lt;= 1000</code></li>
	<li><code>arr1[i]</code>&nbsp;和&nbsp;<code>arr2[i]</code>&nbsp;都是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
	<li><code>arr1</code>&nbsp;和&nbsp;<code>arr2</code>&nbsp;都没有前导0</li>
</ul>

## 解法

### 方法一：模拟

我们遍历两个数组，从最低位开始，记两个数组当前位的数字为 $a$ 和 $b$，进位为 $c$，三个数相加的结果为 $x$。

-   先将进位 $c$ 置为 $0$。
-   如果 $x \geq 2$，由于 $(-2)^{i} + (-2)^{i} = -(-2)^{i+1}$，所以我们可以将 $x$ 减去 $2$，并向高位进位 $-1$。
-   如果 $x = -1$，由于 $-(-2)^{i} = (-2)^{i} + (-2)^{i+1}$，所以我们可以将 $x$ 置为 $1$，并向高位进位 $1$。

然后，我们将 $x$ 加入到答案数组中，然后继续处理下一位。

遍历结束后，去除答案数组中末尾的 $0$，并将数组反转，即可得到最终的答案。

时间复杂度 $O(\max(n, m))$，其中 $n$ 和 $m$ 分别是两个数组的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

相似题目：

-   [1017. 负二进制转换](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1017.Convert%20to%20Base%20-2/README.md)

<!-- tabs:start -->

```python
class Solution:
    def addNegabinary(self, arr1: List[int], arr2: List[int]) -> List[int]:
        i, j = len(arr1) - 1, len(arr2) - 1
        c = 0
        ans = []
        while i >= 0 or j >= 0 or c:
            a = 0 if i < 0 else arr1[i]
            b = 0 if j < 0 else arr2[j]
            x = a + b + c
            c = 0
            if x >= 2:
                x -= 2
                c -= 1
            elif x == -1:
                x = 1
                c += 1
            ans.append(x)
            i, j = i - 1, j - 1
        while len(ans) > 1 and ans[-1] == 0:
            ans.pop()
        return ans[::-1]
```

```java
class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1, j = arr2.length - 1;
        List<Integer> ans = new ArrayList<>();
        for (int c = 0; i >= 0 || j >= 0 || c != 0; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            c = 0;
            if (x >= 2) {
                x -= 2;
                c -= 1;
            } else if (x == -1) {
                x = 1;
                c += 1;
            }
            ans.add(x);
        }
        while (ans.size() > 1 && ans.get(ans.size() - 1) == 0) {
            ans.remove(ans.size() - 1);
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(x -> x).toArray();
    }
}
```

```cpp
class Solution {
public:
    vector<int> addNegabinary(vector<int>& arr1, vector<int>& arr2) {
        int i = arr1.size() - 1, j = arr2.size() - 1;
        vector<int> ans;
        for (int c = 0; i >= 0 || j >= 0 || c; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            c = 0;
            if (x >= 2) {
                x -= 2;
                c -= 1;
            } else if (x == -1) {
                x = 1;
                c += 1;
            }
            ans.push_back(x);
        }
        while (ans.size() > 1 && ans.back() == 0) {
            ans.pop_back();
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func addNegabinary(arr1 []int, arr2 []int) (ans []int) {
	i, j := len(arr1)-1, len(arr2)-1
	for c := 0; i >= 0 || j >= 0 || c != 0; i, j = i-1, j-1 {
		x := c
		if i >= 0 {
			x += arr1[i]
		}
		if j >= 0 {
			x += arr2[j]
		}
		c = 0
		if x >= 2 {
			x -= 2
			c -= 1
		} else if x == -1 {
			x = 1
			c += 1
		}
		ans = append(ans, x)
	}
	for len(ans) > 1 && ans[len(ans)-1] == 0 {
		ans = ans[:len(ans)-1]
	}
	for i, j = 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}
```

```ts
function addNegabinary(arr1: number[], arr2: number[]): number[] {
    let i = arr1.length - 1,
        j = arr2.length - 1;
    const ans: number[] = [];
    for (let c = 0; i >= 0 || j >= 0 || c; --i, --j) {
        const a = i < 0 ? 0 : arr1[i];
        const b = j < 0 ? 0 : arr2[j];
        let x = a + b + c;
        c = 0;
        if (x >= 2) {
            x -= 2;
            c -= 1;
        } else if (x === -1) {
            x = 1;
            c += 1;
        }
        ans.push(x);
    }
    while (ans.length > 1 && ans[ans.length - 1] === 0) {
        ans.pop();
    }
    return ans.reverse();
}
```

```cs
public class Solution {
    public int[] AddNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.Length - 1, j = arr2.Length - 1;
        List<int> ans = new List<int>();
        for (int c = 0; i >= 0 || j >= 0 || c != 0; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            c = 0;
            if (x >= 2) {
                x -= 2;
                c -= 1;
            } else if (x == -1) {
                x = 1;
                c = 1;
            }
            ans.Add(x);
        }
        while (ans.Count > 1 && ans[ans.Count - 1] == 0) {
            ans.RemoveAt(ans.Count - 1);
        }
        ans.Reverse();
        return ans.ToArray();
    }
}
```

<!-- tabs:end -->

<!-- end -->
