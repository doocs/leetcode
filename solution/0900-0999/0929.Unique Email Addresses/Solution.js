const numUniqueEmails2 = function (emails) {
  const emailFilter = function (str) {
    let index = str.search(/@/);
    let s = str.substring(0, index);
    let s2 = str.substring(index + 1, str.length);
    let res = "";
    for (let i = 0; i < s.length; i++) {
      if (s[i] === "+") break;
      if (s[i] === ".") continue;
      res = res + s[i];
    }
    return res + s2;
  };

  let arr = [];
  for (let i = 0; i < emails.length; i++) {
    let t = emailFilter(emails[i]);
    if (arr.indexOf(t) === -1) {
      arr.push(t);
    }
  }
  return arr.length;
};

const numUniqueEmails = function (emails) {
  let arr = emails.map((str) => {
    let index = str.search(/@/);
    let s = str.substring(0, index);
    let s2 = str.substring(index + 1, str.length);
    let res = "";
    for (let i = 0; i < s.length; i++) {
      if (s[i] === "+") break;
      if (s[i] === ".") continue;
      res = res + s[i];
    }
    return res + s2;
  });
  let set = new Set(arr);
  return set.size;
};
