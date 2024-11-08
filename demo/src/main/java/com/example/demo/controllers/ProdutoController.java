
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController<Produto> {

    private List<Produto> produtos = new ArrayList<>();

    // Endpoint para buscar todos os produtos (GET)
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtos;
    }

    // Endpoint para buscar um produto específico pelo ID (GET)
    @GetMapping("/{id}")
    public Produto obterProduto(@PathVariable int id) {
        return produtos.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst()
                .orElse(null); // Retorna null se o produto não for encontrado
    }

    // Endpoint para adicionar um novo produto (POST)
    @PostMapping
    public String adicionarProduto(@RequestBody Produto produto) {
        produtos.add(produto);
        return "Produto adicionado: " + produto.getNome();
    }

    // Endpoint para atualizar um produto existente (PUT)
    @PutMapping("/{id}")
    public String atualizarProduto(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (produto != null) {
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            return "Produto atualizado: " + produto.getNome();
        } else {
            return "Produto não encontrado!";
        }
    }

    // Endpoint para remover um produto (DELETE)
    @DeleteMapping("/{id}")
    public String removerProduto(@PathVariable int id) {
        boolean removed = produtos.removeIf(produto -> produto.getId() == id);
        return removed ? "Produto removido!" : "Produto não encontrado!";
    }

    // Endpoint para atualizar parcialmente um produto (PATCH)
    @PatchMapping("/{id}")
    public String atualizarPreco(@PathVariable int id, @RequestBody double novoPreco) {
        Produto produto = produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (produto != null) {
            produto.setPreco(novoPreco);
            return "Preço do produto atualizado para: " + novoPreco;
        } else {
            return "Produto não encontrado!";
        }
    }

    @GetMapping("/categoria/{categoria}")
    public List<Produto> buscarPorCategoria(@PathVariable String categoria) {
        return produtos.stream()
                .filter(produto -> produto.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());

        @GetMapping("/preco")
        public List<Produto> buscarPorPreco
        (
                    @RequestParam
        double min,
                    @RequestParam double max
            
        
            ) {
                return produtos.stream()
                    .filter(produto -> produto.getPreco() >= min && produto.getPreco() <= max)
                    .collect(Collectors.toList());
        }

        @RestController
        @RequestMapping("/api/produtos")
        public class ProdutoController {

            private List<Produto> produtos; // Supondo que produtos já foi inicializado

            // Método personalizado para buscar produtos com estoque baixo
            @GetMapping("/estoque-baixo")
            public List<Produto> buscarProdutosBaixoEstoque(@RequestParam int limite) {
                return produtos.stream()
                        .filter(produto -> produto.getQuantidade() < limite)
                        .collect(Collectors.toList());
            }
        }

    }

}
