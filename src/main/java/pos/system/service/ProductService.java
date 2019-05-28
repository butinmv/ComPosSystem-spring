package pos.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.system.entities.Company;
import pos.system.entities.Product;
import pos.system.repo.ProductRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) { productRepository.save(product); }

    public Iterable<Product> findAllByCompany(Company company) {
        return productRepository.findAllByCompany(company);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
