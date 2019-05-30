package pos.system.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pos.system.dto.*;
import pos.system.entities.*;
import pos.system.repo.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class MainService {

    private final CompanyRepository companyRepository;
    private final PositionRepository positionRepository;
    private final StaffRepository staffRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CheckRepository checkRepository;

    @Autowired
    public MainService(CompanyRepository companyRepository,
                       PositionRepository positionRepository,
                       StaffRepository staffRepository,
                       CategoryRepository categoryRepository,
                       ProductRepository productRepository,
                       CheckRepository checkRepository) {
        this.companyRepository = companyRepository;
        this.positionRepository = positionRepository;
        this.staffRepository = staffRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.checkRepository = checkRepository;
    }

    // Бизнес-логика для работы с сущностью "company"
    public void saveCompany(CompanyDTO companyDTO) {
        companyRepository.save(
                new Company(
                companyDTO.getUsername(),
                companyDTO.getPassword(),
                companyDTO.getNameCompany(),
                companyDTO.getSite(),
                companyDTO.getTin(),
                companyDTO.getAddress(),
                companyDTO.getPhone(),
                true,
                Collections.singleton(Role.USER)));
    }

    // TODO: переделать в return CompanyDTO, разобраться с паролем
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

//    public void deleteCompany(Long id) {
//        companyRepository.deleteById(id);
//    }

    // Бизнес-логика для работы с сущностью "position"
    @Transactional
    public void savePosition(PositionDTO positionDTO) {
        Position position = new Position(positionDTO.getName(), companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        positionRepository.save(position);
    }

    public ArrayList<PositionDTO> findAllPositionByCompany() {
        ArrayList<PositionDTO> positionsDTO = new ArrayList<>();
        Iterable<Position> positions;
        positions = positionRepository.findAllByCompany(companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Position position : positions) {
            positionsDTO.add(new PositionDTO(position.getId(), position.getName()));
        }
        return positionsDTO;
    }

    public PositionDTO findPositionById(Long id) {
        Position position = positionRepository.findById(id).get();
        return new PositionDTO(position.getId(), position.getName());
    }

    @Transactional
    public void savePositionById(Long id, PositionDTO positionDTO) {
        Position position = positionRepository.findById(id).get();
        position.setName(positionDTO.getName());
        positionRepository.save(position);
    }

    @Transactional
    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }

    // Бизнес-логика для работы с сущностью "staff"
    @Transactional
    public void saveStaff(StaffDTO staffDTO) {
        staffRepository.save(new Staff(staffDTO.getName(), staffDTO.getSurname(), staffDTO.getEmail(), staffDTO.getPassword(),
                companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()),
                positionRepository.findByName(staffDTO.getPosition())));
    }

    public ArrayList<StaffDTO> findAllStaffByCompany() {
        ArrayList<StaffDTO> staffsDTO = new ArrayList<>();
        Iterable<Staff> staffs;
        staffs = staffRepository.findAllByCompany(companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Staff staff : staffs) {
            staffsDTO.add(new StaffDTO(staff.getId(), staff.getName(), staff.getSurname(), staff.getEmail(), staff.getPassword(), staff.getPosition().getName()));
        }
        return staffsDTO;
    }

    public StaffDTO findStaffById(Long id) {
        Staff staff = staffRepository.findById(id).get();
        return new StaffDTO(staff.getId(), staff.getName(), staff.getSurname(), staff.getEmail(), staff.getPassword(), staff.getPosition().getName());
    }

    @Transactional
    public void saveStaffById(Long id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(id).get();
        staff.setName(staffDTO.getName());
        staff.setSurname(staffDTO.getSurname());
        staff.setEmail(staffDTO.getEmail());
        staff.setPassword(staffDTO.getPassword());
        staff.setPosition(positionRepository.findByName(staffDTO.getPosition()));
        staffRepository.save(staff);
    }

    @Transactional
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    // Бизнес-логика для работы с сущностью "category"
    @Transactional
    public void saveCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName(), companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        categoryRepository.save(category);
    }

    public Iterable<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public ArrayList<CategoryDTO> findAllCategoryByCompany() {
        ArrayList<CategoryDTO> categoriesDTO = new ArrayList<>();
        Iterable<Category> categories;
        categories = categoryRepository.findAllByCompany(companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Category category : categories) {
            categoriesDTO.add(new CategoryDTO(category.getId(), category.getName()));
        }
        return categoriesDTO;
    }

    public CategoryDTO findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).get();
        return new CategoryDTO(category.getId(), category.getName());
    }

    @Transactional
    public void saveCategoryById(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).get();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Бизнес-логика для работы с сущностью "product"
    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        productRepository.save(new Product(productDTO.getName(), productDTO.getWholePrice(), productDTO.getMarkup(), productDTO.getRetailPrice(),
                productDTO.getBarcode(), companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()),
                categoryRepository.findByName(productDTO.getCategory())));
    }

    public ArrayList<ProductDTO> findAllProductByCompany() {
        ArrayList<ProductDTO> productsDTO = new ArrayList<>();
        Iterable<Product> products;
        products = productRepository.findAllByCompany(companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Product product : products) {
            productsDTO.add(new ProductDTO(product.getId(), product.getName(), product.getWholePrice(), product.getMarkup(), product.getRetailPrice(), product.getBarcode(), product.getCategory().getName()));
        }
        return productsDTO;
    }

    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product.getId(), product.getName(), product.getWholePrice(), product.getMarkup(), product.getRetailPrice(), product.getBarcode(), product.getCategory().getName());
    }

    @Transactional
    public void saveProductById(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).get();
        product.setName(productDTO.getName());
        product.setWholePrice(productDTO.getWholePrice());
        product.setMarkup(productDTO.getMarkup());
        product.setRetailPrice(productDTO.getRetailPrice());
        product.setBarcode(productDTO.getBarcode());
        product.setCategory(categoryRepository.findByName(productDTO.getCategory()));
        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {productRepository.deleteById(id);
    }

    // Бизнес-логика для работы с сущностью "check"
    // TODO: сделать CheckDTO
    public Iterable<Check> findAllCheckByCompany() {
        return checkRepository.findAllByCompany(companyRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

//    // Фрмирование категорий для передачи по API
//    public String getAllCategoryAPI() {
//        Iterable<Category> categories = categoryRepository.findAll();
//        ArrayList<CategoryAPI> categoriesAPI = new ArrayList<>();
//        for (Category category : categories) {
//            ArrayList<ProductAPI> productsAPI = new ArrayList<>();
//            for (Product product: category.getProducts()) {
//                productsAPI.add(new ProductAPI(product.getId(), product.getName(), product.getRetailPrice()));
//            }
//            categoriesAPI.add(new CategoryAPI(category.getId(), category.getName(), productsAPI));
//        }
//        return new ObjectMapper().writeValueAsString(categories);
//    }
}
