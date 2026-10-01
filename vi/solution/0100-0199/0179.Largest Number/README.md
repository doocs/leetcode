---
comments: true
difficulty: Medium
tags:
    - Greedy
    - Array
    - String
    - Sorting
---

<!-- problem:start -->

# [179. Largest Number](https://leetcode.com/problems/largest-number)

[中文文档](/solution/0100-0199/0179.Largest%20Number/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một danh sách các số nguyên không âm <code>nums</code>, hãy sắp xếp chúng sao cho chúng tạo thành số lớn nhất và trả về số đó.</p>

<p>Vì kết quả có thể rất lớn, bạn cần trả về một chuỗi thay vì một số nguyên.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [10,2]
<strong>Đầu ra:</strong> &quot;210&quot;
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,30,34,5,9]
<strong>Đầu ra:</strong> &quot;9534330&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Để tạo số lớn nhất bằng phép nối, thứ tự số và thứ tự từ điển đơn thuần đều không phù hợp: $9$ phải đứng trước $98$ vì $998>989$. $n\le 100$. So sánh $a+b$ với $b+a$ để sắp xếp hai chuỗi, sắp xếp theo đó rồi nối chúng lại. Nếu ký tự đầu tiên là $0$, mọi giá trị đều bằng không, vì vậy trả về $\texttt{"0"}$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        nums = [str(v) for v in nums]
        nums.sort(key=cmp_to_key(lambda a, b: 1 if a + b < b + a else -1))
        return "0" if nums[0] == "0" else "".join(nums)
```

#### Java

```java
class Solution {
    public String largestNumber(int[] nums) {
        List<String> vs = new ArrayList<>();
        for (int v : nums) {
            vs.add(v + "");
        }
        vs.sort((a, b) -> (b + a).compareTo(a + b));
        if ("0".equals(vs.get(0))) {
            return "0";
        }
        return String.join("", vs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string largestNumber(vector<int>& nums) {
        vector<string> vs;
        for (int v : nums) vs.push_back(to_string(v));
        sort(vs.begin(), vs.end(), [](string& a, string& b) {
            return a + b > b + a;
        });
        if (vs[0] == "0") return "0";
        string ans;
        for (string v : vs) ans += v;
        return ans;
    }
};
```

#### Go

```go
func largestNumber(nums []int) string {
	vs := make([]string, len(nums))
	for i, v := range nums {
		vs[i] = strconv.Itoa(v)
	}
	sort.Slice(vs, func(i, j int) bool {
		return vs[i]+vs[j] > vs[j]+vs[i]
	})
	if vs[0] == "0" {
		return "0"
	}
	return strings.Join(vs, "")
}
```

#### C#

```cs
public class Comparer: IComparer<string> {
    public int Compare(string left, string right) {
        return Compare(left, right, 0, 0);
    }

    private int Compare(string left, string right, int lBegin, int rBegin) {
        var len = Math.Min(left.Length - lBegin, right.Length - rBegin);
        for (var i = 0; i < len; ++i) {
            if (left[lBegin + i] != right[rBegin + i]) {
                return left[lBegin + i] < right[rBegin + i] ? -1 : 1;
            }
        }

        if (left.Length - lBegin == right.Length - rBegin) {
            return 0;
        }
        if (left.Length - lBegin > right.Length - rBegin) {
            return Compare(left, right, lBegin + len, rBegin);
        }
        else {
            return Compare(left, right, lBegin, rBegin + len);
        }
    }
}

public class Solution {
    public string LargestNumber(int[] nums) {
        var sb = new StringBuilder();
        var strs = nums.Select(n => n.ToString(CultureInfo.InvariantCulture)).OrderByDescending(s => s, new Comparer());

        var nonZeroOccurred = false;
        foreach (var str in strs) {
            if (!nonZeroOccurred && str == "0") continue;
            sb.Append(str);
            nonZeroOccurred = true;
        }
        return sb.Length == 0 ? "0" : sb.ToString();
    }
}
```

#### TypeScript

```ts
function largestNumber(nums: number[]): string {
    nums.sort((a, b) => {
        const [ab, ba] = [String(a) + String(b), String(b) + String(a)];
        return +ba - +ab;
    });

    return nums[0] ? nums.join('') : '0';
}
```

#### JavaScript

```js
function largestNumber(nums) {
    nums.sort((a, b) => {
        const [ab, ba] = [String(a) + String(b), String(b) + String(a)];
        return +ba - +ab;
    });

    return nums[0] ? nums.join('') : '0';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
