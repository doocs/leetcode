# Changelog

## 1.0.0 — 01/10/2026

Bản base đầu tiên: hợp nhất quy tắc fidelity/style/protection/QA từ ba repo; giải quyết policy comment/string và residual-language scan; tách source/format/domain/workflow; thêm context/bootstrap, assignment, evidence/hash, resume, per-unit sync và release quyền rõ ràng.

Bổ sung ba profile khởi tạo, templates, source audit và 32 tình huống regression ở trạng thái specification-only. Không triển khai thay đổi lên repo nguồn và không tuyên bố benchmark đa model.

## Quy ước thay đổi về sau

Sửa wording không đổi nghĩa: patch. Thêm module/policy tương thích: minor. Thay authority, schema hoặc default làm thay đổi hành vi hiện hữu: major, kèm migration note và regression liên quan. Đây là quy ước của pack; không cho agent tự bump version để lách approval.
