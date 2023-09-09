# [2676. Throttle](https://leetcode.com/problems/throttle)

[中文文档](/solution/2600-2699/2676.Throttle/README.md)

## Description

<p>Given a function <code>fn</code> and&nbsp;a time in milliseconds <code>t</code>, return&nbsp;a <strong>throttled</strong> version of that function.</p>

<p>A <strong>throttled</strong> function is first called without delay and then, for a time interval of <code>t</code> milliseconds, can&#39;t be executed but should store the latest function arguments provided to call <code>fn</code> with them after the end of the delay.</p>

<p>For instance, <code>t = 50ms</code>, and the function was called at <code>30ms</code>, <code>40ms</code>, and <code>60ms</code>. The first function call would block calling functions for the following <code>t</code> milliseconds. The second function call would save arguments, and the third call arguments should overwrite currently stored arguments from the second call because the second and third calls are called before <code>80ms</code>. Once the delay has passed, the throttled function should be called with the latest arguments provided during the delay period, and it should also create another delay period of <code>80ms + t</code>.</p>

<p><img alt="Throttle Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2676.Throttle/images/screen-shot-2023-04-08-at-120313-pm.png" style="width: 1156px; height: 372px;" />The above diagram&nbsp;shows how throttle&nbsp;will transform&nbsp;events. Each rectangle represents 100ms and the throttle&nbsp;time is 400ms. Each color represents a different set of inputs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> t = 100, calls = [{&quot;t&quot;:20,&quot;inputs&quot;:[1]}]
<strong>Output:</strong> [{&quot;t&quot;:20,&quot;inputs&quot;:[1]}]
<strong>Explanation:</strong> The 1st call is always called without delay
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> t = 50, calls = [{&quot;t&quot;:50,&quot;inputs&quot;:[1]},{&quot;t&quot;:75,&quot;inputs&quot;:[2]}]
<strong>Output:</strong> [{&quot;t&quot;:50,&quot;inputs&quot;:[1]},{&quot;t&quot;:100,&quot;inputs&quot;:[2]}]
<strong>Explanation:</strong> 
The 1st is called a function with arguments (1) without delay.
The 2nd is called at 75ms, within the delay period because 50ms + 50ms = 100ms, so the next call can be reached at 100ms. Therefore, we save arguments from the 2nd call to use them at the callback of the 1st call.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> t = 70, calls = [{&quot;t&quot;:50,&quot;inputs&quot;:[1]},{&quot;t&quot;:75,&quot;inputs&quot;:[2]},{&quot;t&quot;:90,&quot;inputs&quot;:[8]},{&quot;t&quot;: 140, &quot;inputs&quot;:[5,7]},{&quot;t&quot;: 300, &quot;inputs&quot;: [9,4]}]
<strong>Output:</strong> [{&quot;t&quot;:50,&quot;inputs&quot;:[1]},{&quot;t&quot;:120,&quot;inputs&quot;:[8]},{&quot;t&quot;:190,&quot;inputs&quot;:[5,7]},{&quot;t&quot;:300,&quot;inputs&quot;:[9,4]}]
<strong>Explanation:</strong> 
The 1st is called a function with arguments (1) without delay.
The 2nd is called at 75ms within the delay period because 50ms + 70ms = 120ms, so it should only save arguments.&nbsp;
The 3rd is also called within the delay period, and because we need just the latest function arguments, we overwrite previous ones. After the delay period, we do a callback at 120ms with saved arguments. That callback makes another delay period of 120ms + 70ms = 190ms so that the next function can be called at 190ms.
The 4th is called at 140ms in the delay period, so it should be called as a callback at 190ms. That will create another delay period of 190ms + 70ms = 260ms.
The 5th is called at 300ms, but it is after 260ms, so it should be called immediately and should create another delay period of 300ms + 70ms = 370ms.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= t &lt;= 1000</code></li>
	<li><code>1 &lt;= calls.length &lt;= 10</code></li>
	<li><code>0 &lt;= calls[i].t &lt;= 1000</code></li>
	<li><code>0 &lt;= calls[i].inputs[j], calls[i].inputs.length &lt;= 10</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
type F = (...args: any[]) => void;

const throttle = (fn: F, t: number): F => {
    let pending = false;
    let nextArgs;
    const wrapper = (...args) => {
        nextArgs = args;
        if (!pending) {
            fn(...args);
            pending = true;
            nextArgs = undefined;
            setTimeout(() => {
                pending = false;
                if (nextArgs) wrapper(...nextArgs);
            }, t);
        }
    };
    return wrapper;
};

/**
 * const throttled = throttle(console.log, 100);
 * throttled("log"); // logged immediately.
 * throttled("log"); // logged at t=100ms.
 */
```

<!-- tabs:end -->
