# [2094. 找出 3 位偶数](https://leetcode.cn/problems/finding-3-digit-even-numbers)

[English Version](/solution/2000-2099/2094.Finding%203-Digit%20Even%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>digits</code> ，其中每个元素是一个数字（<code>0 - 9</code>）。数组中可能存在重复元素。</p>

<p>你需要找出 <strong>所有</strong> 满足下述条件且 <strong>互不相同</strong> 的整数：</p>

<ul>
	<li>该整数由 <code>digits</code> 中的三个元素按 <strong>任意</strong> 顺序 <strong>依次连接</strong> 组成。</li>
	<li>该整数不含 <strong>前导零</strong></li>
	<li>该整数是一个 <strong>偶数</strong></li>
</ul>

<p>例如，给定的 <code>digits</code> 是 <code>[1, 2, 3]</code> ，整数 <code>132</code> 和 <code>312</code> 满足上面列出的全部条件。</p>

<p>将找出的所有互不相同的整数按 <strong>递增顺序</strong> 排列，并以数组形式返回<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = [2,1,3,0]
<strong>输出：</strong>[102,120,130,132,210,230,302,310,312,320]
<strong>解释：</strong>
所有满足题目条件的整数都在输出数组中列出。 
注意，答案数组中不含有 <strong>奇数</strong> 或带 <strong>前导零</strong> 的整数。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = [2,2,8,8,2]
<strong>输出：</strong>[222,228,282,288,822,828,882]
<strong>解释：</strong>
同样的数字（0 - 9）在构造整数时可以重复多次，重复次数最多与其在 <code>digits</code> 中出现的次数一样。 
在这个例子中，数字 8 在构造 288、828 和 882 时都重复了两次。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>digits = [3,7,5]
<strong>输出：</strong>[]
<strong>解释：</strong>
使用给定的 digits 无法构造偶数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;=&nbsp;digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findEvenNumbers(self, digits: List[int]) -> List[int]:
        ans = []
        counter = Counter(digits)
        for i in range(100, 1000, 2):
            t = []
            k = i
            while k:
                t.append(k % 10)
                k //= 10
            cnt = Counter(t)
            if all([counter[i] >= cnt[i] for i in range(10)]):
                ans.append(i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] counter = count(digits);
        List<Integer> ans = new ArrayList<>();
        for (int i = 100; i < 1000; i += 2) {
            int[] t = new int[3];
            for (int j = 0, k = i; k > 0; ++j) {
                t[j] = k % 10;
                k /= 10;
            }
            int[] cnt = count(t);
            if (check(counter, cnt)) {
                ans.add(i);
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    private boolean check(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < 10; ++i) {
            if (cnt1[i] < cnt2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] count(int[] nums) {
        int[] counter = new int[10];
        for (int num : nums) {
            ++counter[num];
        }
        return counter;
    }
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function findEvenNumbers(digits: number[]): number[] {
    let record = new Array(10).fill(0);
    for (let digit of digits) {
        record[digit]++;
    }
    let ans = [];
    for (let i = 100; i < 1000; i += 2) {
        if (check(record, String(i))) {
            ans.push(i);
        }
    }
    return ans;
}

function check(target: Array<number>, digits: string): boolean {
    let record = new Array(10).fill(0);
    for (let digit of digits) {
        record[digit]++;
    }

    for (let i = 0; i < 10; i++) {
        if (record[i] > target[i]) return false;
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findEvenNumbers(vector<int>& digits) {
        vector<int> counter = count(digits);
        vector<int> ans;
        for (int i = 100; i < 1000; i += 2) {
            vector<int> t(3);
            for (int j = 0, k = i; k > 0; ++j) {
                t[j] = k % 10;
                k /= 10;
            }
            vector<int> cnt = count(t);
            if (check(counter, cnt)) ans.push_back(i);
        }
        return ans;
    }

    vector<int> count(vector<int>& nums) {
        vector<int> counter(10);
        for (int num : nums) ++counter[num];
        return counter;
    }

    bool check(vector<int>& cnt1, vector<int>& cnt2) {
        for (int i = 0; i < 10; ++i)
            if (cnt1[i] < cnt2[i])
                return false;
        return true;
    }
};
```

### **Go**

```go
func findEvenNumbers(digits []int) []int {
	counter := count(digits)
	var ans []int
	for i := 100; i < 1000; i += 2 {
		t := make([]int, 3)
		k := i
		for j := 0; k > 0; j++ {
			t[j] = k % 10
			k /= 10
		}
		cnt := count(t)
		if check(counter, cnt) {
			ans = append(ans, i)
		}
	}
	return ans
}

func count(nums []int) []int {
	counter := make([]int, 10)
	for _, num := range nums {
		counter[num]++
	}
	return counter
}

func check(cnt1, cnt2 []int) bool {
	for i := 0; i < 10; i++ {
		if cnt1[i] < cnt2[i] {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
