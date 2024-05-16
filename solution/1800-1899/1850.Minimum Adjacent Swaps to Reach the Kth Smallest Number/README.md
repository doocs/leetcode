---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1850.Minimum%20Adjacent%20Swaps%20to%20Reach%20the%20Kth%20Smallest%20Number/README.md
rating: 2073
source: 第 239 场周赛 Q3
tags:
    - 贪心
    - 双指针
    - 字符串
---

# [1850. 邻位交换的最小次数](https://leetcode.cn/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number)

[English Version](/solution/1800-1899/1850.Minimum%20Adjacent%20Swaps%20to%20Reach%20the%20Kth%20Smallest%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个表示大整数的字符串 <code>num</code> ，和一个整数 <code>k</code> 。</p>

<p>如果某个整数是 <code>num</code> 中各位数字的一个 <strong>排列</strong> 且它的 <strong>值大于</strong> <code>num</code> ，则称这个整数为 <strong>妙数</strong> 。可能存在很多妙数，但是只需要关注 <strong>值最小</strong> 的那些。</p>

<ul>
	<li>例如，<code>num = "5489355142"</code> ：

    <ul>
    	<li>第 1 个最小妙数是 <code>"5489355214"</code></li>
    	<li>第 2 个最小妙数是 <code>"5489355241"</code></li>
    	<li>第 3 个最小妙数是 <code>"5489355412"</code></li>
    	<li>第 4 个最小妙数是 <code>"5489355421"</code></li>
    </ul>
    </li>

</ul>

<p>返回要得到第 <code>k</code> 个 <strong>最小妙数</strong> 需要对 <code>num</code> 执行的 <strong>相邻位数字交换的最小次数</strong> 。</p>

<p>测试用例是按存在第 <code>k</code> 个最小妙数而生成的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = "5489355142", k = 4
<strong>输出：</strong>2
<strong>解释：</strong>第 4 个最小妙数是 "5489355421" ，要想得到这个数字：
- 交换下标 7 和下标 8 对应的位："5489355<strong>14</strong>2" -&gt; "5489355<strong>41</strong>2"
- 交换下标 8 和下标 9 对应的位："54893554<strong>12</strong>" -&gt; "54893554<strong>21</strong>"
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = "11112", k = 4
<strong>输出：</strong>4
<strong>解释：</strong>第 4 个最小妙数是 "21111" ，要想得到这个数字：
- 交换下标 3 和下标 4 对应的位："111<strong>12</strong>" -&gt; "111<strong>21</strong>"
- 交换下标 2 和下标 3 对应的位："11<strong>12</strong>1" -&gt; "11<strong>21</strong>1"
- 交换下标 1 和下标 2 对应的位："1<strong>12</strong>11" -&gt; "1<strong>21</strong>11"
- 交换下标 0 和下标 1 对应的位："<strong>12</strong>111" -&gt; "<strong>21</strong>111"
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = "00123", k = 1
<strong>输出：</strong>1
<strong>解释：</strong>第 1 个最小妙数是 "00132" ，要想得到这个数字：
- 交换下标 3 和下标 4 对应的位："001<strong>23</strong>" -&gt; "001<strong>32</strong>"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= num.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>num</code> 仅由数字组成</li>
</ul>

## 解法

### 方法一：求下一个排列 + 逆序对

我们可以调用 $k$ 次 `next_permutation` 函数，得到第 $k$ 个最小妙数 $s$。

接下来，我们只需要计算 $num$ 需要经过多少次交换才能变成 $s$ 即可。

我们先考虑一个简单的情况，即 $num$ 中的数字都不相同。在这种情况下，我们可以直接把 $num$ 中的数字字符映射为下标。例如 $num$ 等于 `"54893"`，而 $s$ 等于 `"98345"`。我们将 $num$ 中的每个数字映射为下标，即：

$$
\begin{aligned}
num[0] &= 5 &\rightarrow& \quad 0 \\
num[1] &= 4 &\rightarrow& \quad 1 \\
num[2] &= 8 &\rightarrow& \quad 2 \\
num[3] &= 9 &\rightarrow& \quad 3 \\
num[4] &= 3 &\rightarrow& \quad 4 \\
\end{aligned}
$$

那么 $s$ 中的每个数字映射为下标，就是 `"32410"`。这样，将 $num$ 变成 $s$ 所需要的交换次数，就等于 $s$ 映射后的下标数组的逆序对数。

如果 $num$ 中存在相同的数字，那么我们可以使用一个数组 $d$ 来记录每个数字出现的下标，其中 $d[i]$ 表示数字 $i$ 出现的下标列表。为了使得交换次数尽可能少，在将 $s$ 映射为下标数组时，我们只需要按顺序贪心地选择 $d$ 中对应数字的下标即可。

最后，我们可以直接使用双重循环来计算逆序对数，也可以使用树状数组来优化。

时间复杂度 $O(n \times (k + n))$，空间复杂度 $O(n)$。其中 $n$ 是 $num$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def getMinSwaps(self, num: str, k: int) -> int:
        def next_permutation(nums: List[str]) -> bool:
            n = len(nums)
            i = n - 2
            while i >= 0 and nums[i] >= nums[i + 1]:
                i -= 1
            if i < 0:
                return False
            j = n - 1
            while j >= 0 and nums[j] <= nums[i]:
                j -= 1
            nums[i], nums[j] = nums[j], nums[i]
            nums[i + 1 : n] = nums[i + 1 : n][::-1]
            return True

        s = list(num)
        for _ in range(k):
            next_permutation(s)
        d = [[] for _ in range(10)]
        idx = [0] * 10
        n = len(s)
        for i, c in enumerate(num):
            j = ord(c) - ord("0")
            d[j].append(i)
        arr = [0] * n
        for i, c in enumerate(s):
            j = ord(c) - ord("0")
            arr[i] = d[j][idx[j]]
            idx[j] += 1
        return sum(arr[j] > arr[i] for i in range(n) for j in range(i))
```

```java
class Solution {
    public int getMinSwaps(String num, int k) {
        char[] s = num.toCharArray();
        for (int i = 0; i < k; ++i) {
            nextPermutation(s);
        }
        List<Integer>[] d = new List[10];
        Arrays.setAll(d, i -> new ArrayList<>());
        int n = s.length;
        for (int i = 0; i < n; ++i) {
            d[num.charAt(i) - '0'].add(i);
        }
        int[] idx = new int[10];
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = d[s[i] - '0'].get(idx[s[i] - '0']++);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[i]) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean nextPermutation(char[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i < 0) {
            return false;
        }
        int j = n - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            --j;
        }
        swap(nums, i++, j);
        for (j = n - 1; i < j; ++i, --j) {
            swap(nums, i, j);
        }
        return true;
    }

    private void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

```cpp
class Solution {
public:
    int getMinSwaps(string num, int k) {
        string s = num;
        for (int i = 0; i < k; ++i) {
            next_permutation(begin(s), end(num));
        }
        vector<int> d[10];
        int n = num.size();
        for (int i = 0; i < n; ++i) {
            d[num[i] - '0'].push_back(i);
        }
        int idx[10]{};
        vector<int> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = d[s[i] - '0'][idx[s[i] - '0']++];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[i]) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func getMinSwaps(num string, k int) (ans int) {
	s := []byte(num)
	for ; k > 0; k-- {
		nextPermutation(s)
	}
	d := [10][]int{}
	for i, c := range num {
		j := int(c - '0')
		d[j] = append(d[j], i)
	}
	idx := [10]int{}
	n := len(s)
	arr := make([]int, n)
	for i, c := range s {
		j := int(c - '0')
		arr[i] = d[j][idx[j]]
		idx[j]++
	}
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			if arr[j] > arr[i] {
				ans++
			}
		}
	}
	return
}

func nextPermutation(nums []byte) bool {
	n := len(nums)
	i := n - 2
	for i >= 0 && nums[i] >= nums[i+1] {
		i--
	}
	if i < 0 {
		return false
	}
	j := n - 1
	for j >= 0 && nums[j] <= nums[i] {
		j--
	}
	nums[i], nums[j] = nums[j], nums[i]
	for i, j = i+1, n-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	return true
}
```

```ts
function getMinSwaps(num: string, k: number): number {
    const n = num.length;
    const s = num.split('');
    for (let i = 0; i < k; ++i) {
        nextPermutation(s);
    }
    const d: number[][] = Array.from({ length: 10 }, () => []);
    for (let i = 0; i < n; ++i) {
        d[+num[i]].push(i);
    }
    const idx: number[] = Array(10).fill(0);
    const arr: number[] = [];
    for (let i = 0; i < n; ++i) {
        arr.push(d[+s[i]][idx[+s[i]]++]);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (arr[j] > arr[i]) {
                ans++;
            }
        }
    }
    return ans;
}

function nextPermutation(nums: string[]): boolean {
    const n = nums.length;
    let i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    if (i < 0) {
        return false;
    }
    let j = n - 1;
    while (j >= 0 && nums[i] >= nums[j]) {
        j--;
    }
    [nums[i], nums[j]] = [nums[j], nums[i]];
    for (i = i + 1, j = n - 1; i < j; ++i, --j) {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
    return true;
}
```

<!-- tabs:end -->

<!-- end -->
