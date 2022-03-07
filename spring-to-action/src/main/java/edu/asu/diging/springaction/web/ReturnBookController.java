package edu.asu.diging.springaction.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.asu.diging.simpleusers.core.service.IUserManager;
import edu.asu.diging.springaction.core.model.Book;
import edu.asu.diging.springaction.core.service.BookManager;

@Controller
public class ReturnBookController {

    @Autowired
    private BookManager bookManager;
    
    @Autowired
    private IUserManager userManager;
    
    @RequestMapping(value="/admin/book/return", method=RequestMethod.GET)
    public String show(Model model, Principal principal) {
        model.addAttribute("books", bookManager.findByUser(userManager.findByUsername(principal.getName())));
        return "admin/books/return";
    }
    
    @RequestMapping(value="/auth/book/{bookId}/Return", method=RequestMethod.POST)
    public String Return(@PathVariable("bookId") String bookId, Principal principal, RedirectAttributes redirectAttrs) {
        Book book = bookManager.get(new Long(bookId));
        if (book != null) {
            if (!book.isAvailable()) {
                bookManager.Return(userManager.findByUsername(principal.getName()), book);
                redirectAttrs.addFlashAttribute("msg", "Book succesfully returned.");
            } else  {
                redirectAttrs.addFlashAttribute("msg", "Book has already been returned.");
            }
        }
        return "redirect:/admin/book/return";
    }
}