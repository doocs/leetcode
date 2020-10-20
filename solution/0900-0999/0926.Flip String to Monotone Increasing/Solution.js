const minFlipsMonoIncr = function (S) {
  let n = S.length;
  let f = [];
  for (let i = 0; i < n + 1; i++) {
    f.push(0);
  }
  for (let i = 0; i < n; i++) {
    f[i + 1] = f[i] + (S[i] === "1");
  }
  let ans = n;
  for (let i = 0; i <= n; i++) {
    let a = f[i];
    let b = n - i - (f[n] - f[i]);
    ans = Math.min(ans, b + a);
  }
  return ans;
};
