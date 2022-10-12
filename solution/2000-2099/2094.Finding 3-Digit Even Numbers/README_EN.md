# [2094. Finding 3-Digit Even Numbers](https://leetcode.com/problems/finding-3-digit-even-numbers)

[中文文档](/solution/2000-2099/2094.Finding%203-Digit%20Even%20Numbers/README.md)

## Description

<p>You are given an integer array <code>digits</code>, where each element is a digit. The array may contain duplicates.</p>

<p>You need to find <strong>all</strong> the <strong>unique</strong> integers that follow the given requirements:</p>

<ul>
	<li>The integer consists of the <strong>concatenation</strong> of <strong>three</strong> elements from <code>digits</code> in <strong>any</strong> arbitrary order.</li>
	<li>The integer does not have <strong>leading zeros</strong>.</li>
	<li>The integer is <strong>even</strong>.</li>
</ul>

<p>For example, if the given <code>digits</code> were <code>[1, 2, 3]</code>, integers <code>132</code> and <code>312</code> follow the requirements.</p>

<p>Return <em>a <strong>sorted</strong> array of the unique integers.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = [2,1,3,0]
<strong>Output:</strong> [102,120,130,132,210,230,302,310,312,320]
<strong>Explanation:</strong> All the possible integers that follow the requirements are in the output array. 
Notice that there are no <strong>odd</strong> integers or integers with <strong>leading zeros</strong>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = [2,2,8,8,2]
<strong>Output:</strong> [222,228,282,288,822,828,882]
<strong>Explanation:</strong> The same digit can be used as many times as it appears in digits. 
In this example, the digit 8 is used twice each time in 288, 828, and 882. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = [3,7,5]
<strong>Output:</strong> []
<strong>Explanation:</strong> No <strong>even</strong> integers can be formed using the given digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
