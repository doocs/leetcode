---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3470.Permutations%20IV/README_EN.md
tags:
    - Array
    - Math
    - Combinatorics
    - Enumeration
---

<!-- problem:start -->

# [3470. Permutations IV](https://leetcode.com/problems/permutations-iv)

[中文文档](/solution/3400-3499/3470.Permutations%20IV/README.md)

## Description

<!-- description:start -->

<p>Given two integers, <code>n</code> and <code>k</code>, an <strong>alternating permutation</strong> is a permutation of the first <code>n</code> positive integers such that no <strong>two</strong> adjacent elements are both odd or both even.</p>

<p>Return the <strong>k-th</strong> <strong>alternating permutation</strong> sorted in <em>lexicographical order</em>. If there are fewer than <code>k</code> valid <strong>alternating permutations</strong>, return an empty list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,4,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically-sorted alternating permutations of <code>[1, 2, 3, 4]</code> are:</p>

<ol>
	<li><code>[1, 2, 3, 4]</code></li>
	<li><code>[1, 4, 3, 2]</code></li>
	<li><code>[2, 1, 4, 3]</code></li>
	<li><code>[2, 3, 4, 1]</code></li>
	<li><code>[3, 2, 1, 4]</code></li>
	<li><code>[3, 4, 1, 2]</code> &larr; 6th permutation</li>
	<li><code>[4, 1, 2, 3]</code></li>
	<li><code>[4, 3, 2, 1]</code></li>
</ol>

<p>Since <code>k = 6</code>, we return <code>[3, 4, 1, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically-sorted alternating permutations of <code>[1, 2, 3]</code> are:</p>

<ol>
	<li><code>[1, 2, 3]</code></li>
	<li><code>[3, 2, 1]</code> &larr; 2nd permutation</li>
</ol>

<p>Since <code>k = 2</code>, we return <code>[3, 2, 1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically-sorted alternating permutations of <code>[1, 2]</code> are:</p>

<ol>
	<li><code>[1, 2]</code></li>
	<li><code>[2, 1]</code></li>
</ol>

<p>There are only 2 alternating permutations, but <code>k = 3</code>, which is out of range. Thus, we return an empty list <code>[]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

from typing import List
from math import factorial
import heapq

class Solution:
    def permute(self, xxy: int, yyz: int) -> List[int]:

        kasu = {}
        nnss = []
        majs = []
        ajwi = heapq.heappush
        laoq = []

        zzp = [i for i in range(1, xxy + 1) if i % 2 == 1]
        zzq = [i for i in range(1, xxy + 1) if i % 2 == 0]

        ppp = []
        nxr = None

        for pps in range(xxy):
            if pps == 0:
                cnd = sorted(zzp + zzq)
            else:
                cnd = zzp if nxr == 1 else zzq

            fff = False
            for cndt in cnd:
                if cndt % 2 == 1:
                    nxt = 0
                    noo = len(zzp) - 1
                    nee = len(zzq)
                else:
                    nxt = 1
                    noo = len(zzp)
                    nee = len(zzq) - 1

                llq = noo + nee
                if llq == 0:
                    cnt = 1
                else:
                    if nxt == 1:
                        if noo != (llq + 1) // 2 or nee != llq // 2:
                            cnt = 0
                        else:
                            cnt = factorial(noo) * factorial(nee)
                    else:
                        if nee != (llq + 1) // 2 or noo != llq // 2:
                            cnt = 0
                        else:
                            cnt = factorial(noo) * factorial(nee)

                ajwi(nnss, cnt)
                ajwi(majs, llq)

                if cnt >= yyz:
                    ppp.append(cndt)
                    if cndt % 2 == 1:
                        zzp.remove(cndt)
                        nxr = 0
                    else:
                        zzq.remove(cndt)
                        nxr = 1
                    fff = True
                    break
                else:
                    yyz -= cnt

            ajwi(laoq, len(ppp))

            if not fff:
                return []
        return ppp

```

#### Java

```java

import java.util.*;

class DPHelper {
    static final long ok = 10000000000000000L;
    long[][][] dp = new long[101][101][2];
    boolean[][][] vis = new boolean[101][101][2];

    long compute(int o, int e, int p) {
        if (o == 0 && e == 0) return 1;
        if (vis[o][e][p]) return dp[o][e][p];

        long r = 0;
        if (p == 1) {
            if (o == 0) r = 0;
            else r = o * compute(o - 1, e, 0);
        } else {
            if (e == 0) r = 0;
            else r = e * compute(o, e - 1, 1);
        }

        if (r > ok) r = ok;
        vis[o][e][p] = true;
        dp[o][e][p] = r;
        return r;
    }
}

class SortHelper {
    void sortList(ArrayList<Integer> list) {
        Collections.sort(list);
    }
}

class PermutationHelper {
    List<Integer> buildPermutation(int p, ArrayList<Integer> O, ArrayList<Integer> E, long k, DPHelper d) {
        List<Integer> ans = new ArrayList<>();
        if (O.size() + E.size() == 0) return ans;
        int i = 0;

        if (p == 1) {
            while (i < O.size()) {
                long cnt = d.compute(O.size() - 1, E.size(), 0);
                if (k > cnt) {
                    k -= cnt;
                    i++;
                } else {
                    int x = O.get(i);
                    O.remove(i);
                    ans.add(x);
                    ans.addAll(buildPermutation(0, O, E, k, d));
                    return ans;
                }
            }
        } else {
            while (i < E.size()) {
                long cnt = d.compute(O.size(), E.size() - 1, 1);
                if (k > cnt) {
                    k -= cnt;
                    i++;
                } else {
                    int x = E.get(i);
                    E.remove(i);
                    ans.add(x);
                    ans.addAll(buildPermutation(1, O, E, k, d));
                    return ans;
                }
            }
        }
        return ans;
    }

    List<Integer> alternateFormation(ArrayList<Integer> O, ArrayList<Integer> E, long k, DPHelper d, int n, SortHelper s) {
        List<Integer> ans = new ArrayList<>();
        int tot = O.size() + E.size();
        if (tot % 2 == 1) {
            int i = 0;
            while (i < O.size()) {
                long cnt = d.compute(O.size() - 1, E.size(), 0);
                if (k > cnt) {
                    k -= cnt;
                } else {
                    int x = O.get(i);
                    O.remove(i);
                    ans.add(x);
                    ans.addAll(buildPermutation(0, O, E, k, d));
                    return ans;
                }
                i++;
            }
        } else {
            ArrayList<Integer> U = new ArrayList<>();
            U.addAll(O);
            U.addAll(E);
            s.sortList(U);
            int i = 0;
            while (i < U.size()) {
                int x = U.get(i);
                if (O.contains(x)) {
                    long cnt = d.compute(O.size() - 1, E.size(), 0);
                    if (k > cnt) {
                        k -= cnt;
                    } else {
                        int idx = O.indexOf(x);
                        O.remove(idx);
                        ans.add(x);
                        ans.addAll(buildPermutation(0, O, E, k, d));
                        return ans;
                    }
                } else {
                    long cnt = d.compute(O.size(), E.size() - 1, 1);
                    if (k > cnt) {
                        k -= cnt;
                    } else {
                        int idx = E.indexOf(x);
                        E.remove(idx);
                        ans.add(x);
                        ans.addAll(buildPermutation(1, O, E, k, d));
                        return ans;
                    }
                }
                i++;
            }
        }
        return ans;
    }
}

class Solution {
    public int[] permute(int n, long k) {
        int o = (n + 1) / 2, e = n / 2;
        ArrayList<Integer> O = new ArrayList<>(), E = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) O.add(i);
            else E.add(i);
        }

        SortHelper s = new SortHelper();
        s.sortList(O);
        s.sortList(E);

        DPHelper d = new DPHelper();
        PermutationHelper ph = new PermutationHelper();

        long tot = 0;
        if (n % 2 == 1) tot = d.compute(O.size() - 1, E.size(), 0) * O.size();
        else tot = d.compute(O.size() - 1, E.size(), 0) * O.size() + d.compute(O.size(), E.size() - 1, 1) * E.size();

        if (k > tot) return new int[0];

        List<Integer> res = ph.alternateFormation(O, E, k, d, n, s);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);

        return ans;
    }
}

```

#### C++

```cpp

class Solution {
    long long f[105];
public:
    vector<int> permute(int n, long long k) {
        int i,j;
        for(i=f[0]=1;i<=n;i++)if(f[i-1]>=k)f[i]=k;
        else f[i]=f[i-1]*(i+1>>1);
        if(!(n&1))f[n]*=2;
        if(f[n]<k)return {};
        k--;
        vector<int> ans(n),a[2];
        for(i=0;i<n;i++)a[i&1].push_back(i);
        if(n&1)
        {
            ans[0]=k/f[n-1]*2;
            k-=ans[0]/2*f[n-1];
        }
        else
        {
            ans[0]=k/f[n-1];
            k-=ans[0]*f[n-1];
        }
        a[ans[0]&1].erase(lower_bound(a[ans[0]&1].begin(),a[ans[0]&1].end(),ans[0]));
        for(i=1;i<n;i++)
        {
            if(n&1)
            {
                ans[i]=a[i&1][k/f[n-i-1]];
            }
            else
            {
                ans[i]=a[(ans[0]^i)&1][k/f[n-i-1]];
            }
            k%=f[n-i-1];
            a[ans[i]&1].erase(lower_bound(a[ans[i]&1].begin(),a[ans[i]&1].end(),ans[i]));
        }
        for(i=0;i<n;i++)ans[i]++;
        return ans;
    }
};

```

#### Go

```go

func permute(n int, k int64) []int {
	var f [105]int64
	f[0] = 1
	for i := 1; i <= n; i++ {
		if f[i-1] >= k {
			f[i] = k
		} else {
			f[i] = f[i-1] * int64((i+1)>>1)
		}
	}
	if n%2 == 0 {
		f[n] *= 2
	}
	if f[n] < k {
		return []int{}
	}
	k--
	ans := make([]int, n)
	a := [2][]int{}
	for i := 0; i < n; i++ {
		a[i&1] = append(a[i&1], i)
	}

	if n%2 == 1 {
		ans[0] = int(k/f[n-1]) * 2
		k -= int64(ans[0]/2) * f[n-1]
	} else {
		ans[0] = int(k / f[n-1])
		k -= int64(ans[0]) * f[n-1]
	}

	index := sort.SearchInts(a[ans[0]&1], ans[0])
	a[ans[0]&1] = append(a[ans[0]&1][:index], a[ans[0]&1][index+1:]...)

	for i := 1; i < n; i++ {
		if n%2 == 1 {
			ans[i] = a[i&1][k/f[n-i-1]]
		} else {
			ans[i] = a[(ans[0]^i)&1][k/f[n-i-1]]
		}
		k %= f[n-i-1]

		index = sort.SearchInts(a[ans[i]&1], ans[i])
		a[ans[i]&1] = append(a[ans[i]&1][:index], a[ans[i]&1][index+1:]...)
	}

	for i := 0; i < n; i++ {
		ans[i]++
	}
	return ans
}

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
