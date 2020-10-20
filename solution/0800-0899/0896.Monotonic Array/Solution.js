var isMonotonic = function (A) {
  var check =
    A[0] <= A[A.length - 1] ? (a1, a2) => a1 <= a2 : (a1, a2) => a1 >= a2;
  for (var i = 0; i < A.length - 1; i++) {
    if (!check(A[i], A[i + 1])) {
      return false;
    }
  }
  return true;
};
