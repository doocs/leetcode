# Domain profile: Java và tài liệu JVM

Chỉ nạp khi context chọn profile này. Không đặt edition/JDK mặc định; giữ version và claim của source. Profile hỗ trợ hiểu nghĩa, không sửa tác giả theo Java hiện tại.

## Terminology baseline

| Nhóm             | Cách dùng English-first khi là concept                                                                                 |
| ---------------- | ---------------------------------------------------------------------------------------------------------------------- |
| Core             | class, interface, method, field, constructor, object, instance, reference, primitive, boxed primitive, package, module |
| Type system      | generic, raw type, parameterized type, type parameter, type argument, wildcard, type erasure, subtype, supertype       |
| OOP/API          | inheritance, composition, delegation, override, overload, contract, invariant, precondition, postcondition             |
| Runtime          | JVM, JDK, bytecode, class loader, heap, stack, garbage collection, allocation                                          |
| Concurrency      | thread, lock, synchronization, atomicity, visibility, ordering, happens-before, race condition, deadlock               |
| Object contracts | identity, equality, logical equality, natural ordering, defensive copy, immutable, mutable                             |

Identifier như `Object`, `String`, `equals`, `hashCode`, `@Override`, `List<E>` giữ đúng source, không chuẩn hoá casing của prose thành tên type một cách máy móc.

## Semantic traps cần đối chiếu

Không đánh đồng các cặp: object/reference; identity/equality; override/overload; inheritance/composition; thread safety/atomicity/visibility; checked/unchecked exception; type parameter/type argument; compile time/runtime; encounter order/sorted order; immutability/unmodifiable view.

Giữ đúng quan hệ API contract, direction subtype và điều kiện generic bound. Không “sửa” snippet không compile nếu source cố ý minh hoạ lỗi. Không đổi API cũ/finalizer/thread example sang API mới. SQL/Spring/DB xuất hiện trong một chương vẫn dùng meaning source và glossary tương ứng, không ép tất cả từ thành Java concept.

Nếu sách đánh số `Item N`, giữ identifier tham chiếu theo PROJECT_RULES; không bắt mọi tài liệu Java phải dùng Item. Câu hỏi phỏng vấn vẫn là câu hỏi; ví dụ/đáp án không được tự mở rộng.
