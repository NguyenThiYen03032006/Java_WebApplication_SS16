package com.it210_ss16.controller;

import com.it210_ss16.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {

        model.addAttribute("wallets", walletService.getAllWallets());

        return "transfer";
    }
    @PostMapping("/transfer")
    public String handleTransfer(@RequestParam Long fromId,
                                 @RequestParam Long toId,
                                 @RequestParam BigDecimal amount,
                                 Model model) {
        try {
            walletService.transferMoney(fromId, toId, amount);
            model.addAttribute("message", "Chuyển tiền thành công!");
        } catch (Exception e) {
            model.addAttribute("error", "Giao dịch thất bại: " + e.getMessage());
        }
        return "transfer";
    }
}