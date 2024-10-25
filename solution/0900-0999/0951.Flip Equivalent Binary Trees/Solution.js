function flipEquiv(root1, root2) {
    if (root1 === root2) return true;
    if (!root1 || !root2 || root1?.val !== root2?.val) return false;

    const { left: l1, right: r1 } = root1;
    const { left: l2, right: r2 } = root2;

    return (flipEquiv(l1, l2) && flipEquiv(r1, r2)) || (flipEquiv(l1, r2) && flipEquiv(r1, l2));
}
