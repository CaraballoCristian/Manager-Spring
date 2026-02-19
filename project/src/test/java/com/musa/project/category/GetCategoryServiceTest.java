    package com.musa.project.category;

    import com.musa.project.category.models.Category;
    import com.musa.project.category.repository.CategoryRepository;
    import com.musa.project.category.services.GetCategoryService;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.springframework.http.ResponseEntity;


    import java.util.Arrays;
    import java.util.List;

    import static org.mockito.Mockito.when;
    import static org.assertj.core.api.Assertions.assertThat;

    @ExtendWith(MockitoExtension.class)
    public class GetCategoryServiceTest {

        @Mock
        private CategoryRepository categoryRepository;

        @InjectMocks
        private GetCategoryService getCategoryService;

        // ---- RETURNS ELEMENTS ----
        @Test
        void getCategoryService_returnsList() {

            // Arrange
            List<Category> categoryList = Arrays.asList(
                    new Category("FINANCE"),
                    new Category("FOOD"),
                    new Category("HEALTH"),
                    new Category("SPORTS"),
                    new Category("TECH"));

            when(categoryRepository.findAll()).thenReturn(categoryList);

            // Act
            ResponseEntity<List<String>> response = getCategoryService.execute(null);

            // Assert
            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(response.getBody()).containsExactly("FINANCE", "FOOD", "HEALTH", "SPORTS", "TECH");
        }

        // ---- RETURNS EMPTY LIST ----
        @Test
        void getCategoryService_returnsEmptyList() {
            // Arrange
            when(categoryRepository.findAll()).thenReturn(List.of());

            // Act
            ResponseEntity<List<String>> response = getCategoryService.execute(null);

            // Assert
            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(response.getBody()).isEmpty();
        }

    }
