package pos.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.system.entities.Category;
import pos.system.entities.Company;
import pos.system.repo.CategoryRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Iterable<Category> findAllByCompany(Company company) {
        return categoryRepository.findAllByCompany(company);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
