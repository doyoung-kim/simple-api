package com.example.simpleapi.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.example.simpleapi.model.Board;
import com.example.simpleapi.model.ResultMessage;
import com.example.simpleapi.repository.BoardRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/boards")
@Slf4j
@RequiredArgsConstructor
public class DemoApiController {
	private final BoardRepository boardRepository;


	@GetMapping(value = "/totalCount")
	public int totalCount() throws Exception {
		return  (int) boardRepository.count();
	}

    @GetMapping()
	public  List<Board>  list(@RequestParam(value="size", defaultValue = "10")  int size , @RequestParam(value="page", defaultValue = "1") int page) throws Exception {
		
		PageRequest pageRequest = PageRequest.of((page - 1), size, Sort.Direction.DESC, "num");
		Page<Board> pageList = boardRepository.findAll(pageRequest);
		List<Board> boardList = pageList.getContent();
		return boardList;
		

	}

	@GetMapping("/search")
	public  List<Board>  search(@RequestParam(value = "search") String search) throws Exception {
		return boardRepository.searchByStartsWith(search+"%");
	}


	@GetMapping("/{num}")
	public Board view(@PathVariable(value = "num") int num) throws Exception {
		
		Board board = boardRepository.findById(num).orElse(new Board());
		return board;

	}
	@GetMapping("/{num}/view2")
	public Board view2(@PathVariable(value = "num") int num) throws Exception {		
		return view(num);		 

	}

	@PostMapping()
	public ResponseEntity<ResultMessage> insert( @RequestBody  Board paramBoard) throws Exception {		
		Board board  = boardRepository.save(paramBoard);
		return getResponseEntity(board);
		 
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<ResultMessage> updateBoard(@RequestBody Board board) {
		int result = boardRepository.updateBoard(board);
		return getResponseEntity(result);
		 
	}
	@DeleteMapping("/{num}")
	public ResponseEntity<ResultMessage> delete(@PathVariable(value = "num") int num) throws Exception {
		boardRepository.deleteById(num);
		return getResponseEntity(1);

	}


	private ResponseEntity<ResultMessage> getResponseEntity(int result) {
		if(result > 0) {				 
			return ResponseEntity.ok(new ResultMessage("Y", "정상"));
		}			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMessage("N", "오류"));	
	}
	private ResponseEntity<ResultMessage> getResponseEntity(Board  board) {
		if(board.getNum() > 0) {				 
			return ResponseEntity.ok(new ResultMessage("Y", "정상"));
		}			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMessage("N", "오류"));	
	}
    
}
