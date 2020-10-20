const numSubarrayWithSum = function (A, S) {
  let count = 0;
  for (let i = 0; i < A.length; i++) {
    if (A[i] === 1) count++;
  }
  if (S > count) return 0;
  let count2 = 0;
  let res = 0;
  for (let i = 0; i < A.length; i++) {
    if (S > count - count2) break;
    let t = 0;
    for (let j = i; j < A.length; j++) {
      t += A[j];
      if (t === S) {
        res++;
        for (let k = j + 1; k < A.length; k++) {
          if (A[k] === 0) res++;
          else break;
        }
        break;
      }
    }
    if (A[i] === 1) count2++;
  }
  return res;
};
