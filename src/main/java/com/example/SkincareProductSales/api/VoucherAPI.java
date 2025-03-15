package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Voucher;
import com.example.SkincareProductSales.entity.request.VoucherRequest;
import com.example.SkincareProductSales.enums.VoucherStatusEnum;
import com.example.SkincareProductSales.service.VoucherService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voucher")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class VoucherAPI {

    @Autowired
    VoucherService voucherService;

    @PostMapping
    public ResponseEntity createVoucher(@Valid @RequestBody VoucherRequest voucherRequest){
        Voucher newVoucher = voucherService.createVoucher(voucherRequest);
        return ResponseEntity.ok(newVoucher);
    }

    @GetMapping
    public ResponseEntity getAllVoucher(){
        List<Voucher> vouchers = voucherService.getAllVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("{id}")
    public ResponseEntity getVoucher(@PathVariable long id){
        Voucher voucher = voucherService.getVoucherById(id);
        return ResponseEntity.ok(voucher);
    }

    @GetMapping("/getAllVoucherIsDeleted")
    public ResponseEntity getAllVoucherIsDeleted(){
        List<Voucher> vouchers = voucherService.getAllVouchersIsDeleted();
        return ResponseEntity.ok(vouchers);
    }

    @PutMapping("{id}")
    public ResponseEntity updateVoucher(@PathVariable long id, @Valid @RequestBody VoucherRequest voucherRequest){
        Voucher updateVoucher = voucherService.updateVoucher(id, voucherRequest);
        return ResponseEntity.ok(updateVoucher);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateVoucherStatus (@PathVariable long id, @RequestParam VoucherStatusEnum statusEnum){
        Voucher voucher = voucherService.updateVoucherStatus(id, statusEnum);
        return ResponseEntity.ok(voucher);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteVoucher(@PathVariable long id){
        Voucher deleteVoucher = voucherService.deleteVoucher(id);
        return ResponseEntity.ok(deleteVoucher);
    }
}
