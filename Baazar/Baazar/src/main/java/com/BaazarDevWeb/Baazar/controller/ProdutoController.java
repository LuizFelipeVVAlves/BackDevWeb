package com.BaazarDevWeb.Baazar.controller;

import com.BaazarDevWeb.Baazar.model.Interacao;
import com.BaazarDevWeb.Baazar.model.Produto;
import com.BaazarDevWeb.Baazar.model.ResultadoPaginado;
import com.BaazarDevWeb.Baazar.service.InteracaoService;
import com.BaazarDevWeb.Baazar.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("produtos")  // http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private InteracaoService interacaoService;

    @GetMapping   // Requisição do tipo GET para http://localhost:8080/produtos
    public List<Produto> recuperarProdutos() {
//        if (true) {
//            throw new RuntimeException("Deu erro no servidor");
//        }
        return produtoService.recuperarProdutos();
    }

    // Requisição do tipo GET para http://localhost:8080/produtos/id
    @GetMapping("{idProduto}")
    public Produto recuperarProdutoPorId(@PathVariable("idProduto") long id) {
        return produtoService.recuperarProdutoPorId(id);
    }

    // Requisição do tipo GET para http://localhost:8080/produtos/categoria/frutas
    /*@GetMapping("categoria/{slugCategoria}")
    public List<Produto> recuperarProdutosPorSlugCategoria(@PathVariable("slugCategoria") String slugCategoria) {
        return produtoService.recuperarProdutosPorSlugCategoria(slugCategoria);
    }*/

    // Requisição do tipo POST para http://localhost:8080/produtos
    @PostMapping
    public Produto cadastraProduto(@RequestBody Produto produto) {

        produto.setDataCadastro(LocalDate.now());
        produto.setDisponivel(true);
        Interacao interacao = new Interacao(produto);
        produtoService.cadastrarProduto(produto);
        interacaoService.cadastrarInteracao(interacao);

        return produto;
    }

    @PutMapping
    public Produto alterarProduto(@RequestBody Produto produto) {
        return produtoService.alterarProduto(produto);
    }

    @DeleteMapping  ("{idProduto}")   // http://localhost:8080/produtos/1
    public void removerProduto(@PathVariable("idProduto") long id) {
        produtoService.removerProduto(id);
    }

    // Entradas
    // - pagina corrente
    // - tamanho da página
    // Saídas:
    // - total de itens
    // - total de páginas
    // - pagina corrente
    // - itens da página corrente

    // Requisição do tipo GET para
    // http://localhost:8080/produtos/paginacao?pagina=0&tamanho=5&nome=ce
    @GetMapping("paginacao")
    public ResultadoPaginado<Produto> recuperarProdutosComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "5") int tamanho,
            @RequestParam(value = "nome", defaultValue = "") String nome) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Produto> page = produtoService.recuperarProdutosComPaginacao(pageable, nome);
        ResultadoPaginado<Produto> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }

}
